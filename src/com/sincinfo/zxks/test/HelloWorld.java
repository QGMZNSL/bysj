/*    */ package com.sincinfo.zxks.test;
/*    */ 
/*    */ import com.sincinfo.zxks.common.action.WebActionSupport;
/*    */ import com.sincinfo.zxks.common.db.DbConn;
/*    */ import com.sincinfo.zxks.common.db.DbObjException;
/*    */ import com.sincinfo.zxks.common.db.DbObjPool;
/*    */ import java.io.IOException;
/*    */ import java.io.PrintWriter;
/*    */ import java.sql.Connection;
/*    */ import java.sql.ResultSet;
/*    */ import java.sql.SQLException;
/*    */ import java.sql.Statement;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ 
/*    */ public class HelloWorld extends WebActionSupport
/*    */ {
/*    */   public void HelloWorld()
/*    */   {
/* 25 */     String hw = "HelloWorld!";
/* 26 */     PrintWriter pw = null;
/*    */     try {
/* 28 */       pw = this.response.getWriter();
/* 29 */       pw.write(hw);
/* 30 */       pw.flush();
/*    */     } catch (IOException e) {
/* 32 */       e.printStackTrace();
/*    */     } finally {
/* 34 */       if (pw != null)
/* 35 */         pw.close();
/*    */     }
/*    */   }
/*    */ 
/*    */   public void HelloDb()
/*    */   {
/* 44 */     DbObjPool dbp = DbObjPool.getInstance();
/* 45 */     DbConn db = null;
/*    */     try {
/* 47 */       db = dbp.getDbConn();
/*    */     } catch (DbObjException e) {
/* 49 */       e.printStackTrace();
/*    */     }
/* 51 */     String sql = "select 'Hello World111111111' as hw from dual";
/* 52 */     String hw = null;
/*    */     try {
/* 54 */       db.st = db.conn.createStatement();
/* 55 */       db.rs = db.st.executeQuery(sql);
/* 56 */       if (db.rs.next())
/* 57 */         hw = db.rs.getString("hw");
/*    */     }
/*    */     catch (SQLException e) {
/* 60 */       e.printStackTrace();
/*    */     } finally {
/* 62 */       dbp.released(db);
/*    */     }
/*    */ 
/* 65 */     PrintWriter pw = null;
/*    */     try {
/* 67 */       pw = this.response.getWriter();
/* 68 */       pw.write(hw);
/* 69 */       pw.flush();
/*    */     } catch (IOException e) {
/* 71 */       e.printStackTrace();
/*    */     } finally {
/* 73 */       if (pw != null)
/* 74 */         pw.close();
/*    */     }
/*    */   }
/*    */ }

/* Location:           D:\war\ZK_CORE\WEB-INF\classes\
 * Qualified Name:     com.sincinfo.zxks.test.HelloWorld
 * JD-Core Version:    0.6.0
 */