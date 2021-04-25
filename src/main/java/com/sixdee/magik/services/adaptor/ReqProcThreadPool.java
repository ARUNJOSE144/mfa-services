package com.sixdee.magik.services.adaptor;

import java.util.LinkedList;

/*
 * @author Sudharsan
 */

public class ReqProcThreadPool {

	private int noOfConnections;
	private int newNoOfConns;

	private LinkedList<FileDownlaodTO> taskList = null;
	private ReqProcWorkerThread[] workerThreadArray;

	private ReqProcWorkerThread[] workerThreadArrayTemp;

	public ReqProcThreadPool() {
		// Default Process
	}

	public ReqProcThreadPool(int connections) {

		System.out.println("---------------------------------------> Pool Creating ......... " + connections);

		noOfConnections = Math.max(1, connections);
		taskList = new LinkedList<FileDownlaodTO>();

		System.out.println(" --------------------> Intialised the TaskList    ");

		createPool();
	}

	private void createPool() {

		System.out.println(" --------------------> Pool is Creating  ");

		workerThreadArray = new ReqProcWorkerThread[noOfConnections];
		for (int i = 0; i < noOfConnections; i++) {
			boolean isThreadCreated = false;
			while (true) {
				try {
					workerThreadArray[i] = new ReqProcWorkerThread(taskList, i);
					workerThreadArray[i].start();
					isThreadCreated = true;
				} catch (Exception e) {
					/**
					 * if exception occurs don't do anything
					 */
				}
				if (isThreadCreated)
					break;
			}
		}
	}

	public void addTask(FileDownlaodTO reqDTO) {
		synchronized (taskList) {
			taskList.addLast(reqDTO);
			taskList.notify();
		}
	}

	public void shutdown() {
		for (int i = 0; i < noOfConnections; i++) {
			workerThreadArray[i].stopThreadProc();
		}
	}

	public int getNoOfConnections() {
		return noOfConnections;
	}

	public int getTaskSize() {
		return taskList.size();
	}

	public void flushTaskList() {
		synchronized (taskList) {
			taskList.clear();
		}
	}

	public void changeThreadConn() {

		switch (compPrevConn()) {

		case -1:
			decrementThreadProc();
			break;
		case 0:
			break;
		case 1:
			incrementThreadProc();
			break;
		default:
			break;

		}
	}

	/**
	 * Increment the process
	 */
	private void incrementThreadProc() {

		workerThreadArrayTemp = new ReqProcWorkerThread[newNoOfConns];
		for (int i = 0; i < noOfConnections; i++) {
			workerThreadArrayTemp[i] = workerThreadArray[i];
		}

		for (int i = noOfConnections; i < newNoOfConns; i++) {
			boolean isThreadCreated = false;
			while (true) {
				try {
					workerThreadArrayTemp[i] = new ReqProcWorkerThread(taskList, i);
					workerThreadArrayTemp[i].start();
					isThreadCreated = true;
				} catch (Exception e) {
					/**
					 * if exception occurs don't do anything
					 */
				}
				if (isThreadCreated)
					break;
			}
		}
		workerThreadArray = workerThreadArrayTemp;
	}

	private void decrementThreadProc() {
		// int diffVal=noOfConnections-newNoOfConns;
		workerThreadArrayTemp = new ReqProcWorkerThread[newNoOfConns];

		for (int i = 0; i < newNoOfConns; i++) {
			workerThreadArrayTemp[i] = workerThreadArray[i];
		}

		for (int i = newNoOfConns; i < noOfConnections; i++) {
			workerThreadArray[i].setFlagFalse();
		}

		workerThreadArray = workerThreadArrayTemp;

	}

	/**
	 * Check if new more than old or less than old
	 * 
	 * @return
	 */
	private int compPrevConn() {

		int isChangeReq = 0;
		int val = newNoOfConns - noOfConnections;

		if (val < 0) {
			isChangeReq = -1;
		} else if (val > 0) {
			isChangeReq = 1;
		}

		return isChangeReq;
	}

	/**
	 * @return Returns the newNoOfConns.
	 */
	public int getNewNoOfConns() {
		return newNoOfConns;
	}

	/**
	 * @param newNoOfConns The newNoOfConns to set.
	 */
	public void setNewNoOfConns(int newNoOfConns) {
		this.newNoOfConns = newNoOfConns;
	}

}
