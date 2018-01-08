package com.hynu.dao;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.widgets.Table;

import com.hynu.bean.CD_Table;
import com.hynu.biz.Data;
import com.hynu.biz.SaveAndLoadBiz;
import com.hynu.utils.IOUtils;
import com.hynu.utils.Log;
import com.hynu.utils.MathUtil;

public class GameRecord {
	/**
	 * ͨ��LoadFrame��combo��ѡ���ѯ��Ϸ�ĵ����� sl_cd table
	 * �Դ浵��ʹ浵�ļ�����ɾ������
	 */
	private DBHelper<CD_Table> db=new DBHelper<>();
	
	//��ȡ��Ϸ�浵��¼
	public List<CD_Table> getGameRecord(String difficulty){
		List<Object> params=new ArrayList<>();
		String sql="select cd_id, uname, difficulty, schedule, gamedate from sl_users, sl_cd, sl_nd "
				  + "where sl_users.id=sl_cd.id and sl_cd.difficulty_id=sl_nd.difficulty_id and difficulty=? and uname=? order by cd_id desc";
		if(difficulty!=null && !"".equals(difficulty)){
//			sql=sql+"  and difficulty=?";
			params.add(difficulty);
			params.add(Data.user.getUname());
		}
		List<CD_Table> list = null;
		try {
			list = db.select(sql, params, CD_Table.class);
		} catch (Exception e) {
			e.printStackTrace();
			Log.error(e);
		}
		return list;
	}
	
	//�����ݿ���д��浵��¼ 
	public int  writeGameRecord(){
		String sql="insert into sl_cd(id,difficulty_id,  schedule, gameDate) values( ?, ?, ?, sysdate() )";
		List<Object> params=new ArrayList<>();
		params.add(Data.user.getId());
		params.add(Data.difficulty_id);
		params.add(getSchedul());
//		System.out.println(getSchedul());
		int r=db.update(sql, params);
		return r;
	}
	
	//������Ϸ����
//	public Double getSchedul(){
//		Double rightBomb=(double) Data.sl.rightBomb; 
//		Double bombNum=(double) Data.sl.BombNum;
//		Double restBlock=(double) Data.sl.restBlock;
//		Double BlockNum=(double) Data.sl.rowNum*Data.sl.colNum;
////		System.out.println(restBlock+"\t"+BlockNum);
//		Double schedule1=rightBomb/bombNum;
//		Double schedule2=(BlockNum-restBlock-Data.sl.BombNum)/BlockNum;
//		int n=(int) (((schedule2+schedule1)/2*1000+5)/10);
//		Double schedule= ((double)n/100);
////		System.out.println(schedule);
//		return schedule;
//	}
	
	public Double getSchedul(){
		Double schedule1=MathUtil.getXS(Data.sl.rightBomb, Data.sl.BombNum);
		Integer BlockNum= Data.sl.rowNum*Data.sl.colNum;
		Double schedule2=MathUtil.getXS(BlockNum-Data.sl.restBlock-Data.sl.BombNum, BlockNum);
		int n=(int) (((schedule2+schedule1)/2*1000+5)/10);
		Double schedule= ((double)n/100);
		return schedule;
	}
	
	//�õ�ÿ�δ浵��¼�ı��
	
	public Integer getRecordID(){
//		DBHelper<Integer> db=new DBHelper<>();
		String sql="select max(cd_id) as cd_id from sl_cd";
		List<Map<String, String>> list=db.queryAll(sql, null);
		Integer cd_id=null;
		if(list!=null &list.size()>0){
			if(list.get(0).get("cd_id")!=null){
				cd_id=Integer.parseInt(list.get(0).get("cd_id"));
			}else{
			}
		}
		return cd_id;
	}
	
	//��Ϸ����ɾ���浵��¼
	public Integer deleteDBRecord(Integer cd_id){
//		Integer cd_id=Data.sal.id;
		String sql="delete from sl_cd where 1=1 ";
		List<Object> params=new ArrayList<>();
		if(cd_id!=null){
			sql=sql+"  and cd_id=?";
			params.add(cd_id);
		}
		Integer r=db.update(sql, params);
		return r;
	}
	
	//��Ϸ����ɾ���浵�ļ�
	public void deleteFileRecord(Integer cd_id){
		String filePath=IOUtils.filePath;
//		Integer cd_id=Data.sal.id;
		File bomBFile=new File(filePath+File.separator+cd_id+"_"+1+"_tmp.data");
		File perpertyFile=new File(filePath+File.separator+cd_id+"_"+2+"_tmp.data");
		if(bomBFile.exists()  &&  perpertyFile.exists()){
			bomBFile.delete();
			perpertyFile.delete();
		}
	}
	
	//��Ϸ�������Զ�ɾ����Ϸ�浵��¼
	public void deleteRecord(){
		if(Data.sal!=null){
			if(Data.sal.id!=null){
				Integer cd_id=Data.sal.id;
				GameRecord gr=new GameRecord();
				gr.deleteDBRecord(cd_id);
				gr.deleteFileRecord(cd_id);
				//֪ͨcoboˢ��
			}
		}
	}
	
	//�ڶ��������ֶ�ɾ����Ϸ�浵��¼
	public void deleteRecord_1(){
		SaveAndLoadBiz sal=new SaveAndLoadBiz();
		Integer id=sal.getRecordID();
		if(id!=null){
			deleteDBRecord(id);
			deleteFileRecord(id);
		}
	}
	
	
	
	//�жϴ浵����Ϸ�Ƿ����
//		public List isEnd(){
//			Table table=Data.lf.table;
//			Integer id=(Integer) table.getItem(table.getSelectionIndex()).getData("cd_id");
//			String sql="select *from sl_cd where 1=1 and cd_id=?";
//			List<Object> params=new ArrayList<>();
//			if(id!=null){
//				params.add(id);
//			}
//			List<CD_Table> list = null;
//			try {
//				list = db.select(sql, params, CD_Table.class);
//				System.out.println(list);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			return list;
//		}
	
//	public Date select(Integer cd_id){
//		DBHelper<CD_Table> db=new DBHelper<>();
//		String sql="select gameDate from sl_cd where '1'='1' ";
//		List<Object> params =new ArrayList<>();
//		if(cd_id!=null){
//			sql=sql+"  and cd_id=?";
//			params.add(cd_id);
//		}
//		List<CD_Table> list = null;
//		try {
//			list = db.select(sql, params, CD_Table.class);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		if(list!=null && list.size()>0){
//			return list.get(0).getGameDate();
//		}else{
//			return null;
//		}
	
		
//	}
}
