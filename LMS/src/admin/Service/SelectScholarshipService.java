package admin.Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import student.DAO.StudentDao;
import student.DTO.ScholarshipDataBean;
import jdbc.jdbcUtil;
import jdbc.ConnectionProvider;

public class SelectScholarshipService {
	private static SelectScholarshipService instance = new SelectScholarshipService();
	
	public static SelectScholarshipService getInstance(){
		return instance;
	}
	private SelectScholarshipService(){}
	public List<ScholarshipDataBean> getItemList(int id){

		StudentDao studentDao = StudentDao.getInstance();
		Connection conn = null;
		try{
			conn = ConnectionProvider.getConnection();
			List<ScholarshipDataBean> ItemList = studentDao.selectScholarship_All(conn, id);
			return ItemList;
		}catch(SQLException e){
			throw new RuntimeException("DB 에러:"+e.getMessage(), e);
		}finally{
			jdbcUtil.close(conn);
		}
	}
	public List<ScholarshipDataBean> getItemList_content(int id,String year,int grade,String semester){

		StudentDao studentDao = StudentDao.getInstance();
		Connection conn = null;
		try{
			conn = ConnectionProvider.getConnection();
			List<ScholarshipDataBean> ItemList = studentDao.selectScholarship(conn, id,year,grade,semester);
			return ItemList;
		}catch(SQLException e){
			throw new RuntimeException("DB 에러:"+e.getMessage(), e);
		}finally{
			jdbcUtil.close(conn);
		}
	}
	
}
