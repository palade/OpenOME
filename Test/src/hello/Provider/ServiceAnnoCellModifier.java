package hello.Provider;

import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.TableItem;

public class ServiceAnnoCellModifier implements ICellModifier {
	private TableViewer tv;
	public static String[] cols = {"ch","en","jp"};
	public ServiceAnnoCellModifier(TableViewer tv)
	{
		this.tv = tv;
	}
	@Override
	public boolean canModify(Object arg0, String arg1) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Object getValue(Object element, String property) {
		// TODO Auto-generated method stub
		 ServiceAnnotation p = (ServiceAnnotation)element;
         if(property.equals("lang")){
             return new Integer(getNameIndex(p.getLang()));
         }else if(property.equals("value")){
             return p.getValue();
         }
         throw new RuntimeException("error column name : " + property);
	}
	private int getNameIndex(String name){
        for(int i=0;i<cols.length;i++){
            if(cols[i].equals(name)){
                return i;
            }
        }
        return -1;
    }
	@Override
	public void modify(Object element, String property, Object value) {
		// TODO Auto-generated method stub
		TableItem item = (TableItem)element;
        ServiceAnnotation p = (ServiceAnnotation)item.getData();
        if (property.equals("lang")){
            Integer comboIndex = (Integer)value;
            if(comboIndex.intValue() == -1){
                return ;
            }
            String newName = cols[comboIndex.intValue()];
            p.setLang(newName);
        }else if (property.equals("value")){
            String newValue = (String)value;
            if(newValue.equals("")){
                return ;
            }
           
            p.setValue(newValue);
        }else{
            throw new RuntimeException("´íÎóÁÐÃû:" + property);
        }
        tv.update(p, null);
	}

}
