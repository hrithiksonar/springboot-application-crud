package com.customer.model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import com.customer.utility.Constant;

public class TrackCount implements Serializable {

	private static final long serialVersionUID = 1L;
	private int idCount;

	public TrackCount() {
	}

	public TrackCount(int idCount) {
		this.idCount = idCount;
	}

	public int getCount() throws ClassNotFoundException, IOException {
		System.out.println("getCount");
		TrackCount readCount = readCount();
		System.out.println("readCount "+readCount);
		if (readCount == null) {
			writeCount(new TrackCount(1));
			return 1;
		} else {
			writeCount(readCount);
			return readCount.getIdCount() + 1;
		}
	}

	public TrackCount readCount() throws ClassNotFoundException, IOException {
		System.out.println("Inside readcount start");
		FileInputStream fin;

		fin = new FileInputStream(Constant.FILE_PATH);
		System.out.println("1");
		ObjectInputStream oin = new ObjectInputStream(fin);
		System.out.println("2");
		TrackCount readObject = (TrackCount) oin.readObject();
		System.out.println("3");
		System.out.println("readCount end");
		oin.close();
		fin.close();
		return readObject;
	}

	public void writeCount(TrackCount count) throws IOException {
		FileOutputStream fout = new FileOutputStream(Constant.FILE_PATH);
		ObjectOutputStream oos = new ObjectOutputStream(fout);
		count.setIdCount(count.getIdCount() + 1);
		oos.writeObject(count);
		oos.close();
		fout.close();

	}

	public void setIdCount(int idCount) {
		this.idCount = idCount;
	}

	public int getIdCount() {
		return idCount;
	}

	@Override
	public String toString() {
		return "TrackCount [idCount=" + idCount + "]";
	}

}
