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

			for (EObject obj : xmires.getContents())// ���ģ�͵ĸ�
			{
				System.out.println(obj);
				ExtensionMapImpl pi = (ExtensionMapImpl) obj;
				System.out.println(pi.eContents().size());

				for (EObject o : pi.eContents()) {
					ExtensionImpl ex = (ExtensionImpl) o;// �������ģ�͵Ļ
					if (ex.basicGetExtendedObject() instanceof ActivityImpl) {
						final ActivityImpl ai = (ActivityImpl) ex
								.basicGetExtendedObject();// ai��ʾ��ǰģ��

						if (ai != null && ai.getName() != null
								&& ai.getName().equals(name))// ��ai�������봫�������������ͬ����ı���ɫ
						{// �첽���½���
							// ���������߳�ÿ���봫��һ�ε����У������Ҫʹ�����ַ�ʽ������
							Display.getDefault().syncExec(new Runnable() {
								public void run() {
									ai.setColor(ColorHolder.getColor());// ģ�ͷ����ı䣬��Ҫ����editPart��Ϊ������֪ͨ��ͼ������Ӧ�ı仯
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
