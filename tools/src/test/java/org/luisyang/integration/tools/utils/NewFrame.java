package org.luisyang.integration.tools.utils;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.luisyang.integration.tools.util.DESUtil;

public class NewFrame extends JFrame {
	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	private JButton button1;
	private JButton button2;
	private JTextField text1;
	private JTextField text2;
	
	public NewFrame() {
		super();
		this.setSize(300, 300);
		this.getContentPane().setLayout(null);// 设置布局控制器
		this.add(this.getTextField(), null);// 添加文本框
		this.add(this.getTextField1(), null);// 添加文本框
		this.add(this.getButton(), null);// 添加按钮
		this.add(this.getButton2(), null);// 添加按钮
		this.add(this.getLabel(), null);// 添加标签
		this.add(this.getLabel1(), null);// 添加标签
		this.add(this.getLabel2(), null);// 添加标签
		this.setTitle("DES加密解密");// 设置窗口标题

	}

	/**
	 * 设置标签
	 * 
	 * @return 设置好的标签
	 */
	private JLabel getLabel() {
		if (label1 == null) {
			label1 = new JLabel();
			label1.setBounds(34, 49, 53, 18);
			label1.setText("明文:");
		}
		return label1;
	}

	/**
	 * 设置标签
	 * 
	 * @return 设置好的标签
	 */
	private JLabel getLabel1() {
		if (label2 == null) {
			label2 = new JLabel();
			label2.setBounds(34, 98, 53, 18);
			label2.setText("密文:");
		}
		return label2;
	}
	
	private JLabel getLabel2() {
		if (label3 == null) {
			label3 = new JLabel();
			label3.setBounds(100, 220, 100, 18);
			Color color=Color.RED;
			label3.setBackground(color);
			label3.setVisible(false);
		}
		return label3;
	}

	/**
	 * 设置按钮
	 * 
	 * @return 设置好的按钮
	 */
	private JButton getButton() {
		if (button1 == null) {
			button1 = new JButton();
			button1.setBounds(50, 150, 71, 27);
			button1.setText("加密");
			button1.setToolTipText("加密");
			button1.addActionListener(new Button1());// 添加监听器类，其主要的响应都由监听器类的方法实现

		}
		return button1;
	}
	
	private JButton getButton2() {
		if (button2 == null) {
			button2 = new JButton();
			button2.setBounds(150, 150, 71, 27);
			button2.setText("解密");
			button2.setToolTipText("解密");
			button2.addActionListener(new Button2());// 添加监听器类，其主要的响应都由监听器类的方法实现

		}
		return button2;
	}


	/**
	 * 监听器类实现ActionListener接口，主要实现actionPerformed方法
	 * 
	 * @author HZ20232
	 *
	 */
	private class Button1 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String val=text1.getText();
			if(val==null||val.equals("")){
				label3.setText("明文不 能为空");
				label3.setVisible(true);
				return ;
			}
			String str="";
			try {
				str=DESUtil.encrypt(val);
				text2.setText(str);
				label3.setVisible(false);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
	
	private class Button2 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String val=text2.getText();
			if(val==null||val.equals("")){
				label3.setText("密文不 能为空");
				label3.setVisible(true);
				return ;
			}
			String str="";
			try {
				str=DESUtil.decrypt(val);
				text1.setText(str);
				label3.setVisible(false);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	/**
	 * 设定文本域
	 * 
	 * @return
	 */
	private JTextField getTextField() {
		if (text1 == null) {
			text1 = new JTextField();
			text1.setBounds(96, 49, 160, 20);
		}
		return text1;
	}

	private JTextField getTextField1() {
		if (text2 == null) {
			text2 = new JTextField();
			text2.setBounds(96, 98, 160, 20);
		}
		return text2;
	}
}
