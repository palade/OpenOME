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
		 IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();//��ù����ռ��·��
		 IEditorPart activeEditor =PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();//���Editor
		 IEditorInput editorInput = activeEditor.getEditorInput();//���editor������ ���Ǳ༭�Ķ�����˭
		 if(editorInput instanceof FileEditorInput)//�����Ƿ����ļ�����
		 {
			 FileEditorInput input = (FileEditorInput)   editorInput;
			 IFile iFile = input.getFile();//��ȡ��ǰ�����ļ�
			 IProject iProject = iFile.getProject();//����ļ����ڵĹ���
			 System.out.println(iProject.getLocation());
			 IResource[] irs = iProject.members();//members�����������е��ļ�
			 for(int i = 0 ; i < irs.length ; i++)
			 {
				 String ext = irs[i].getFileExtension();//��ȡ�ļ�����չ��
				 if(ext != null && "wsdl".equalsIgnoreCase(ext))//������ǰ�򿪵��ļ��Ĺ����е����е�.wsdl�ļ�
				 {
					 String name = irs[i].getName();//�õ��ļ���
					 if(name != null && name.indexOf("Artifacts") == -1)//�������ļ�
					 {
						 services.add(parse(irs[i].getLocation().toString()));//����parse����
					 }
				 }
			 }
		 }
		 return services;
	}
	private ServiceInfo parse(String path) {//����wsdl·��
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
			System.out.println("���ڿ��Բ鿴Զ��Web���������й������(��Ӧ����Web������,ServiceInfo)");
			System.out.println(serviceInfo.getName() + "�ṩ�Ĳ�����:");
			while (iter.hasNext()) {
				i++;
				OperationInfo oper = (OperationInfo) iter.next();
				System.out.println("");
				System.out.println("����:" + i + " " + oper.getTargetMethodName());
				List inps = oper.getInparameters();
				List outps = oper.getOutparameters();
				if (inps.size() == 0) {
					System.out.println("�˲���������������Ϊ:");
					System.out.println("ִ�д˲�������Ҫ�����κβ���!");
				} else {
					System.out.println("�˲���������������Ϊ:");
					for (Iterator iterator1 = inps.iterator(); iterator1.hasNext();) {
						ParameterInfo element = (ParameterInfo) iterator1.next();
						System.out.println("������Ϊ:" + element.getName());
						System.out.println("��������Ϊ:" + element.getKind());
					}
				}
				if (outps.size() == 0) {
					System.out.println("ִ�д˲����������κβ���!");
				} else {
					System.out.println("�˲������������Ϊ:");
					for (Iterator iterator2 = outps.iterator(); iterator2.hasNext();) {
						ParameterInfo element = (ParameterInfo) iterator2.next();
						System.out.println("������:" + element.getName());
						System.out.println("����Ϊ:" + element.getKind());
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
