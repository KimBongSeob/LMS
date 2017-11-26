package action.commonProfessor;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import board.DAO.LMSDBBean;

public class ListAction implements CommandAction {// 글목록 처리

	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("UTF-8");// 한글 인코딩
		String pageNum = request.getParameter("pageNum");// 페이지 번호
		
		if (pageNum == null) {
			pageNum = "1";
		}
		
		String search = request.getParameter("search");
		int searchn = 0;
		
		if(search == null){
			search = "";
		}else{
			searchn = Integer.parseInt(request.getParameter("searchn"));
		}
		
		int pageSize = 10;// 한 페이지의 글의 개수
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;// 한 페이지의 시작글 번호
		int endRow = currentPage * pageSize;// 한 페이지의 마지막 글번호
		int count = 0;
		int number = 0;

		List articleList = null;
		LMSDBBean dbPro = LMSDBBean.getInstance();// DB연동
		
		if(search.equals("") || search == null)
			count = dbPro.getArticleCount();// 전체 글의 수
		else
			count = dbPro.getArticleCount(searchn,search);

		if (count > 0) {
			if(search.equals("") || search == null){
			articleList = dbPro.getArticles(startRow, endRow);// 현재 페이지에 해당하는 글 목록
		    }else{
			articleList = dbPro.getArticles(startRow, endRow, searchn, search);
		    }
		}else {
			articleList = Collections.EMPTY_LIST;
		}
		

		number = count - (currentPage - 1) * pageSize;// 글목록에 표시할 글번호
		
		// 해당 뷰에서 사용할 속성 // view에서 사용할 데이터는 전부 request/session에 저장
		request.setAttribute("currentPage", new Integer(currentPage));
		request.setAttribute("startRow", new Integer(startRow));
		request.setAttribute("endRow", new Integer(endRow));
		request.setAttribute("count", new Integer(count));
		request.setAttribute("pageSize", new Integer(pageSize));
		request.setAttribute("number", new Integer(number));
		request.setAttribute("articleList", articleList);

		return "/LearningManagementSystem/professor/commonProfessor/MVC/list.jsp";// 해당 view로 리턴. WebContent 하위 경로부터 적는다.
	}
}