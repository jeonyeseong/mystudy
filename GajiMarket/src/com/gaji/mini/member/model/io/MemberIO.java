package com.gaji.mini.member.model.io;

import java.io.*;
import java.util.*;

import com.gaji.mini.member.model.vo.Member;

public class MemberIO {
	private final String dir = "database/";
	private final String fileName = "Members.dat";
	private Map<String, Member> members;

	public Map<String, Member> readFile() {
		try (ObjectInputStream ois = new ObjectInputStream(
				new BufferedInputStream(new FileInputStream(dir + fileName)))) {

			members = (HashMap<String, Member>) ois.readObject();
			if (members == null) {
				return new HashMap<>();
			}
			Set<String> keySet = members.keySet();
			members.putAll(members);

			for (String key : keySet) { // key == ID
				members.put(key, members.get(key));
				// System.out.println(members.get(key));
			}
		} catch (NullPointerException npe) {
			return members;
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
			writeFile(null);
			System.out.println("파일생성");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return members;
	}

	public void writeFile(Map<String, Member> addedMembers) {
		members = addedMembers;
		try (ObjectOutputStream oos = new ObjectOutputStream(
				new BufferedOutputStream(new FileOutputStream(dir + fileName)))) {

			oos.writeObject(members);
			System.out.println(members);
			System.out.println("File saved: database/Members.dat");

		} catch (NullPointerException npe) {
			npe.printStackTrace();
			return;
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
