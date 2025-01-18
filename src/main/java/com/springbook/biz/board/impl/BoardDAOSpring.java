package com.springbook.biz.board.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.springbook.biz.board.BoardVO;


/* Jdbc 설정 완료 -> JdbcTemplate 객체를 이용하여 DAO 클래스 구현
 * 
 * 하는 방법
 * 1. JdbcDaoSupport 클래스 상속
 * 		-> extends JdbcDaoSupport
 * 2. JdbcTemplate 클래스 <bean> 등록, 의존성 주입 (사용 多)
 * 
 * */

@Repository
//1. public class BoardDAOSpring extends JdbcDaoSupport { // JdbcDaoSupport 클래스 상속받아야 getJdbcTemplate()사용가능
public class BoardDAOSpring {
	
	/*1.
	@Autowired
	public void setSuperDataSource(DataSource dataSource) { 
		// JdbcTemplate 객체를 리턴하려면 DataSource 객체를 가지고 있어야 함
		// 따라서 JdbcDaoSupport클래스의 .setDataSource() 호출 -> DataSource 객체를 의존성 주입해야 함.
		super.setDataSource(dataSource);
	}
	 */
	
	
	//2. JdbcTemplate객체에 DataSource 의존성 주입
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	// SQL 명령어들
	private final String BOARD_INSERT = "INSERT INTO board(seq, title, writer, content) "
			+ "VALUES((SELECT NVL(MAX(seq), 0)+1 FROM board), ?, ?, ?)";
	
	private final String BOARD_UPDATE = "UPDATE board SET title=?, content=?, WHERE seq=?";
	
	private final String BOARD_DELETE = "DELETE board WHERE seq=?";
	
	private final String BOARD_GET = "SELECT * FROM board WHERE seq=?";
	
	private final String BOARD_LIST = "SELECT * FROM board ORDER BY seq desc";
	
	// CRUD 기능의 메소드 구현
	// 글 등록
	public void insertBoard(BoardVO vo) {
		System.out.println("===> Spring JDBC로 insertBoard() 기능 처리");
		//getJdbcTemplate().update(BOARD_INSERT, vo.getTitle(),vo.getWriter(), vo.getContent());
		jdbcTemplate.update(BOARD_INSERT, vo.getTitle(),vo.getWriter(), vo.getContent());
	}
	
	// 글 수정
	public void updateBoard(BoardVO vo) {
		System.out.println("===> Spring JDBC로 updateBoard() 기능 처리");
		jdbcTemplate.update(BOARD_UPDATE, vo.getTitle(), vo.getContent(), vo.getSeq());
	}
	
	// 글 삭제
	public void deleteBoard(BoardVO vo) {
		System.out.println("===> Spring JDBC로 deleteBoard() 기능 처리");
		jdbcTemplate.update(BOARD_DELETE, vo.getSeq());
	}
	
	// 글 상세 조회
	public BoardVO getBoard(BoardVO vo) {
		System.out.println("===> Spring JDBC로 getBoard() 기능 처리");
		Object[] args = {vo.getSeq()};
		return jdbcTemplate.queryForObject(BOARD_GET, args, new BoardRowMapper());
	}
	
	// 글 목록 조회
	public List<BoardVO> getBoardList(BoardVO vo) {
		System.out.println("===> Spring JDBC로 getBoardList() 기능 처리");
		return jdbcTemplate.query(BOARD_LIST, new BoardRowMapper());
	}
	
	public class BoardRowMapper implements RowMapper<BoardVO> {
		public BoardVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			BoardVO board = new BoardVO();
			board.setSeq(rs.getInt("SEQ"));
			board.setTitle(rs.getString("TITLE"));
			board.setWriter(rs.getString("WRITER"));
			board.setContent(rs.getString("CONTENT"));
			board.setRegDate(rs.getDate("REGDATE"));
			board.setCnt(rs.getInt("CNT"));
			return board;
		}
	}
}
