package org.luisyang.integration.tools.collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.luisyang.integration.tools.domian.User;

public class ListTest {

	public static void main(String[] args) {
		
		List<User> users = new ArrayList<User>();
		users.add(new User(1,"aaa"));
		users.add(new User(2,"aaab"));
		users.add(new User(3,"aaabc"));
		users.add(new User(4,"aaac"));
		users.add(new User(5,"aaadd"));
		
		//1.第一种遍历方式
		for(Iterator<User> it = users.iterator();it.hasNext();){
			System.out.println(it.next().toString());
		}
		//1.第二种遍历方式
		for(User user : users){
			System.out.println(user.toString());
		}
		//1.第三种遍历方式
		for(int i = 0;i<users.size();i++){
			System.out.println(users.get(i).toString());
		}
		
		
	}
}
