package org.eclipse.bpel.ui.event;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.bpel.common.extension.model.impl.ExtensionImpl;
import org.eclipse.bpel.common.extension.model.impl.ExtensionMapImpl;
import org.eclipse.bpel.model.impl.ActivityImpl;
import org.eclipse.bpel.ui.BPELEditor;
import org.eclipse.bpel.ui.BPELMultipageEditorPart;
import org.eclipse.bpel.ui.actions.ColorHolder;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

import edu.toronto.cs.openome.evaluation.listener.AbstractEvaluationListener;
import edu.toronto.cs.openome.evaluation.listener.EvaluationListener;
import edu.toronto.cs.openome_model.EvaluationLabel;

public class Observer implements IObserver {
	private ResourceSet set;

	private EvaluationListener eListener;
	Map<String, EvaluationLabel> eva;

	public Observer() {
		IWorkbenchWindow iww = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow();
		IWorkbenchPage iwp = iww.getActivePage();
		IEditorPart idp = iwp.getActiveEditor();
		BPELMultipageEditorPart e = (BPELMultipageEditorPart) idp;
		BPELEditor editor = (BPELEditor) e.getActiveEditor();
		set = editor.getResourceSet();

		// eListener = new AbstractEvaluationListener("Example.ood");
	}

	public void update(String name) {
		try {

			XMIResourceImpl xmires = null;
			for (Resource res : set.getResources()) {

				if (res instanceof XMIResourceImpl)
					xmires = (XMIResourceImpl) res;
			}

			for (EObject obj : xmires.getContents())// 获得模型的根
			{
				System.out.println(obj);
				ExtensionMapImpl pi = (ExtensionMapImpl) obj;
				System.out.println(pi.eContents().size());

				for (EObject o : pi.eContents()) {
					ExtensionImpl ex = (ExtensionImpl) o;// 获得所有模型的活动
					if (ex.basicGetExtendedObject() instanceof ActivityImpl) {
						final ActivityImpl ai = (ActivityImpl) ex
								.basicGetExtendedObject();// ai表示当前模型

						if (ai != null && ai.getName() != null
								&& ai.getName().equals(name))// 若ai的名称与传输进来的名称相同，则改变颜色
						{// 异步更新界面
							// 现在是子线程每五秒传输一次的运行，因此需要使用这种方式来进行
							Display.getDefault().syncExec(new Runnable() {
								public void run() {
									ai.setColor(ColorHolder.getColor());// 模型发生改变，需要利用editPart作为控制器通知视图进行相应的变化
								}
							});
							if (eListener != null) {
								eva = new HashMap<String, EvaluationLabel>();
								eva.put(name.split("_")[0],
										EvaluationLabel.SATISFIED);
								eListener.evaluate(eva);
							}

						}
					}
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void addEvaluationListener(EvaluationListener eListener) {
		this.eListener = eListener;
	}
}
