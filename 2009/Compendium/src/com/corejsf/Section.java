package com.corejsf;

import java.util.LinkedList;
import java.util.List;

/**
 * @author IShafigullin
 *
 */
public class Section {

	private String titleKey;
	private String titleName;
	private String image;
	private String directory;
	private int numChapters;
	private List<String> chapterKeys = null;
	private String chapter;

	public void setChapterKeys(List<String> chapterKeys) {
		this.chapterKeys = chapterKeys;
	}

	// PROPERTY: titleKey
	public void setTitleKey(String titleKey) {
		this.titleKey = titleKey;
	}

	public String getTitleKey() {
		return titleKey;
	}

	// PROPERTY: titleKey
	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}

	public String getTitleName() {
		return titleName;
	}

	// PROPERTY: image
	public void setImage(String image) {
		this.image = image;
	}

	public String getImage() {
		return image;
	}

	// PROPERTY: numChapters
	public void setNumChapters(int numChapters) {
		this.numChapters = numChapters;
	}

	public int getNumChapters() {
		return numChapters;
	}

	// PROPERTY: directory
	public void setDirectory(String directory) {
		this.directory = directory;
	}

	public String getDirectory() {
		return directory;
	}

	// PROPERTY: chapter
	public void setChapter(String chapter) {
		this.chapter = chapter;
	}

	public String getChapter() {
		if (chapter == null) {
			chapter = getChapterKeys().get(0);
		}
		return chapter;
	}

	// PROPERTY: chapterKeys
	public List<String> getChapterKeys() {
		if (chapterKeys == null) {
			chapterKeys = new LinkedList<String>();
			for (int i = 1; i <= numChapters; ++i)
				chapterKeys.add("chapter" + i);
		}
		return chapterKeys;
	}
}
