package com.hynu.event;


import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Text;

import com.hynu.biz.Data;
import com.hynu.constant.Constant;
import com.hynu.ui.NanduUI;
import com.hynu.utils.MathUtil;
import com.hynu.utils.UIUtils;

public class Select_NanduAdapter extends SelectionAdapter{
	
	private NanduUI nanduUI;

	public Select_NanduAdapter(NanduUI nanduUI){
		this.nanduUI=nanduUI;
	}
	
	@Override
	public void widgetSelected(SelectionEvent e) {
		if(nanduUI.btnRadioButton.getSelection()){
			nanduUI.setProperty(Constant.rowNum_1, Constant.colNum_1,  Constant.BombNum_1, Constant.width_1, Constant.height_1, Constant.difficulty_id_1);
		}else if(nanduUI.btnRadioButton_1.getSelection()){
			nanduUI.setProperty(Constant.rowNum_2, Constant.colNum_2, Constant.BombNum_2, Constant.width_2, Constant.height_2, Constant.difficulty_id_2);
		}else if(nanduUI.btnRadioButton_2.getSelection()){
			nanduUI.setProperty(Constant.rowNum_3, Constant.colNum_3, Constant.BombNum_3, Constant.width_3, Constant.height_3, Constant.difficulty_id_3);
		}else if(nanduUI.btnRadioButton_3.getSelection()){
			if(checkText(nanduUI.rowText)  &&  checkText(nanduUI.colText)  && checkText(nanduUI.bombText)){
				if(textIsNum(nanduUI.rowText) &&textIsNum(nanduUI.colText)&& textIsNum(nanduUI.bombText)){
					Integer rowNum=Integer.parseInt(nanduUI.rowText.getText());
					Integer colNum=Integer.parseInt(nanduUI.colText.getText());
					Integer bombNum=Integer.parseInt(nanduUI.bombText.getText());
					if(limit(rowNum,colNum, bombNum)){
						if(rowNum!=null && colNum!=null){
							Rectangle r=getWH(rowNum, colNum);
							nanduUI.setProperty(rowNum, colNum, bombNum,  r.width , r.height,Constant.difficulty_id_4 );
						}
					}
				}else{
					UIUtils.showMessageDialog(nanduUI.shell, "必须输入整数", "");
 				}
			}else{
				UIUtils.showMessageDialog(nanduUI.shell, "请正确设置宽高和雷数", "");
			}
		}
		
	}
	
	//根据行列数计算自定义的宽高
	public Rectangle getWH(Integer rowNum, Integer colNum){
		Rectangle r=new Rectangle(0,0,0,0);
		r.width=colNum*38+50;
		r.height=rowNum*38+20+20+100;
		return r;
	}
	
	public boolean checkText(Text text){
		if(text.getText()!=null && !"".equals(text.getText())){
			return true;
		}
		else{
			return false;
		}
	}
	
	public boolean limit(Integer r, Integer c, Integer b){
		if(r<9 || r>15){
			UIUtils.showMessageDialog(Data.gameUI.shell, "输入的行数必须在9-15之间", "");
			return false;
		}else if(c<9 || c>35){
			UIUtils.showMessageDialog(Data.gameUI.shell, "输入的行数必须在9-35之间", "");
			return false;
		}else if(2*r*c/3< b){
			UIUtils.showMessageDialog(Data.gameUI.shell, "设置的雷数太多", "");
			return false;
		}
		return true;
	}
	
	public boolean textIsNum(Text t){
		return MathUtil.isNum(t.getText());
	}
	
}
