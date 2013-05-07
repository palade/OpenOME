package org.eclipse.bpel.ui.automation;

import java.util.Iterator;
import java.util.LinkedList;


import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Platform;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;


public class WSDLParser {
	
	public List<ServiceInfo> parseAllWSDL() throws CoreException
	{
		List<ServiceInfo> services = new LinkedList<ServiceInfo>();
		 IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();//获得工作空间的路径
		 IEditorPart activeEditor =PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();//获得Editor
		 IEditorInput editorInput = activeEditor.getEditorInput();//获得editor的输入 就是编辑的对象是谁
		 if(editorInput instanceof FileEditorInput)//输入是否是文件输入
		 {
			 FileEditorInput input = (FileEditorInput)   editorInput;
			 IFile iFile = input.getFile();//获取当前输入文件
			 IProject iProject = iFile.getProject();//获得文件所在的工程
			 System.out.println(iProject.getLocation());
			 IResource[] irs = iProject.members();//members代表工程下所有的文件
			 for(int i = 0 ; i < irs.length ; i++)
			 {
				 String ext = irs[i].getFileExtension();//获取文件的扩展名
				 if(ext != null && "wsdl".equalsIgnoreCase(ext))//解析当前打开的文件的工程中的所有的.wsdl文件
				 {
					 String name = irs[i].getName();//得到文件名
					 if(name != null && name.indexOf("Artifacts") == -1)//跳过主文件
					 {
						 services.add(parse(irs[i].getLocation().toString()));//调用parse方法
					 }
				 }
			 }
		 }
		 return services;
	}
	private ServiceInfo parse(String path) {//传入wsdl路径
		int i = 0, j = 0;

		try {
			ComponentBuilder builder = new ComponentBuilder();
			ServiceInfo serviceInfo = new ServiceInfo();
//			String wsdllocation = "";
			
//			 IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
//			 IEditorPart activeEditor =PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
//			 IEditorInput editorInput = activeEditor.getEditorInput();
//			 if(editorInput instanceof FileEditorInput)
//			 {
//				 FileEditorInput input = (FileEditorInput)   editorInput;
//				 IFile iFile = input.getFile();
//				 IProject iProject = iFile.getProject();
//				 System.out.println(iProject.getLocation());
//				 wsdllocation = iProject.getLocation()+"/AddService.wsdl";
//			 }
			serviceInfo.setWsdllocation(path);
			serviceInfo = builder.buildserviceinformation(serviceInfo);
			System.out.println("");
			Iterator iter = serviceInfo.getOperations();
			System.out.println("现在可以查看远端Web服务对象的有关情况了(对应本地Web服务类,ServiceInfo)");
			System.out.println(serviceInfo.getName() + "提供的操作有:");
			while (iter.hasNext()) {
				i++;
				OperationInfo oper = (OperationInfo) iter.next();
				System.out.println("");
				System.out.println("操作:" + i + " " + oper.getTargetMethodName());
				List inps = oper.getInparameters();
				List outps = oper.getOutparameters();
				if (inps.size() == 0) {
					System.out.println("此操作所需的输入参数为:");
					System.out.println("执行此操作不需要输入任何参数!");
				} else {
					System.out.println("此操作所需的输入参数为:");
					for (Iterator iterator1 = inps.iterator(); iterator1.hasNext();) {
						ParameterInfo element = (ParameterInfo) iterator1.next();
						System.out.println("参数名为:" + element.getName());
						System.out.println("参数类型为:" + element.getKind());
					}
				}
				if (outps.size() == 0) {
					System.out.println("执行此操作不返回任何参数!");
				} else {
					System.out.println("此操作的输出参数为:");
					for (Iterator iterator2 = outps.iterator(); iterator2.hasNext();) {
						ParameterInfo element = (ParameterInfo) iterator2.next();
						System.out.println("参数名:" + element.getName());
						System.out.println("类型为:" + element.getKind());
					}
				}
				System.out.println("");
			}
			return serviceInfo;
		}

		catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}




}
