package com.sixdee.magik.services.service.impl;

import java.util.List;

import javax.swing.JComponent;
import javax.swing.JFrame;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sixdee.magik.services.dao.ActionFileDao;
import com.sixdee.magik.services.model.ActionFileTO;
import com.sixdee.magik.services.service.ActionFileService;

@Service
public class ActionFileServiceImpl implements ActionFileService {

	public static final String TREE_CHI = "/chi-ontology.xml.gz";
	@Autowired
	ActionFileDao actionFileDao;

	@Override
	public void saveFileDetails(List<ActionFileTO> actionFileTOs) {
		// TODO Auto-generated method stub
		System.out.println("in servive file  service..");
		actionFileDao.saveFileDetails(actionFileTOs);
	}

//	public void datafetch()
//	{
//		 String infile = TREE_CHI;
//	        String label = "name";
//	     
//	        JComponent treeview = TreeView.demo(infile, label);
//	        System.out.println("label>>>"+label);
//	        
//	        JFrame frame = new JFrame("p r e f u s e  |  t r e e v i e w");
//	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//	        frame.setContentPane(treeview);
//	        frame.pack();
//	        frame.setVisible(true);
//	}
//	
//	public static void main(String[] args) {
//		ActionFileServiceImpl actionFileServiceImpl = new ActionFileServiceImpl();
//		actionFileServiceImpl.datafetch();
//		
//	}

}
