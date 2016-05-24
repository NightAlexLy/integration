package org.luisyang.integration.tools.seq;

import java.sql.SQLException;
import java.util.List;

import org.luisyang.integration.tools.domian.Sequence;
import org.luisyang.integration.tools.util.DBHelper;

import junit.framework.TestCase;

public class SeqTest extends TestCase{

	
	public void testSeq(){
		String name = "seq_test";
		int index = 1;
		try {
			List<Sequence> users = DBHelper.executeQuery("select * from sys_sequence where name = ?", Sequence.class,name);
			if(users.size() <=0 ){
				DBHelper.executeSQL("insert into sys_sequence(name,current_value) values(?,?)", name,index);
			}else{
				index =DBHelper.getSeqenceById("select nextval(?) as value",name);
			}
			System.out.println(index);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
