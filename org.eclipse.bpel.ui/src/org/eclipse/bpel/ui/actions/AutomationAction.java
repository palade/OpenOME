package org.eclipse.bpel.ui.actions;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.eclipse.bpel.ui.automation.OperationInfo;
import org.eclipse.bpel.ui.automation.PartnerLinkGenerator;
import org.eclipse.bpel.ui.automation.ServiceInfo;
import org.eclipse.bpel.ui.automation.VariableGenerator;
import org.eclipse.bpel.ui.automation.WSDLParser;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

import org.jdom.JDOMException;

public class AutomationAction implements IWorkbenchWindowActionDelegate {

	@Override
	public void run(IAction action) {
		// TODO Auto-generated method stub
		try {
			List<ServiceInfo> sers = new WSDLParser().parseAllWSDL();//解析wsdl文档，将有用信息填充到databean中-ServiceInfo属性类
			//包括serviceName、方法名、参数等等
			System.out.println("---------------------------------");
			System.out.println(((OperationInfo)sers.get(0).getOperation().get(0)).getInputMessageName());

			for(ServiceInfo ser : sers)	{
				new PartnerLinkGenerator().generateLink(ser.getName(), ((OperationInfo)ser.getOperation().get(0)).getTargetMethodName(), ser.getTargetnamespace(), ser.getWsdllocation());
				new VariableGenerator().generateVariable(ser.getName(),((OperationInfo)ser.getOperation().get(0)).getTargetMethodName(),ser.getTargetnamespace(),ser.getWsdllocation(),((OperationInfo)sers.get(0).getOperation().get(0)).getInputMessageName(),((OperationInfo)sers.get(0).getOperation().get(0)).getOutputMessageName());	
			}
			} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JDOMException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	}
	  
	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(IWorkbenchWindow window) {
		// TODO Auto-generated method stub

	}

}
