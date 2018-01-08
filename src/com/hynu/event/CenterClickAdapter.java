package com.hynu.event;

import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.widgets.Button;
import com.hynu.bean.Bomb;
import com.hynu.biz.SL_Biz;

public class CenterClickAdapter extends MouseAdapter {
	private SL_Biz sl = null;

	public CenterClickAdapter(SL_Biz sl) {
		this.sl = sl;
	}

	@Override
	public void mouseDown(org.eclipse.swt.events.MouseEvent e) {
			if (e.button == 2) {
				if (sl.tsFlag == false) {
					sl.tsFlag = true;
					sl.gameTime+=30000;
				Button button = ((Button) e.getSource());
				Bomb bomb = (Bomb) button.getData();
				if (bomb.isClicked() == true) {
					if (bomb.getBombFlag() % 2 == 0) {
						sl.zuobi(bomb);
						bomb.setBombFlag(bomb.getBombFlag() + 1);
					} else {
						sl.quxiaozuobi();
						bomb.setBombFlag(bomb.getBombFlag() + 1);
						bomb.setRight(false);
					}
				}
			}
		}
	}

}
