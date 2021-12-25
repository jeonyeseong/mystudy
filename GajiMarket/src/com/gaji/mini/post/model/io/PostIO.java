package com.gaji.mini.post.model.io;

import java.io.*;
import java.util.*;

import com.gaji.mini.post.model.vo.Post;

public class PostIO {
	private final String dir = "database/";
	private String fileName = "Post.dat";
	private Map<Integer, Post> posts;

	public Map<Integer, Post> readFile() {
		try (ObjectInputStream ois = new ObjectInputStream(
				new BufferedInputStream(new FileInputStream(dir + fileName)))) {

			posts = (HashMap<Integer, Post>) ois.readObject();
			if (posts == null) {
				return new HashMap<>();
			}
			Set<Integer> keySet = posts.keySet();
			posts.putAll(posts);

			for (Integer key : keySet) { // key == postNo
				posts.put(key, posts.get(key));
				// System.out.println(posts.get(key));
			}
		} catch (NullPointerException npe) {
			return posts;
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
			writeFile(null);
			System.out.println("파일생성");

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return posts;
	}

	public void writeFile(Map<Integer, Post> addedPosts) {
		posts = addedPosts;
		try (ObjectOutputStream oos = new ObjectOutputStream(
				new BufferedOutputStream(new FileOutputStream(dir + fileName)))) {

			oos.writeObject(posts);
			System.out.println(posts);
			System.out.println("File saved: database/Members.dat");

		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
