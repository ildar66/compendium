package com.corejsf;

import java.util.*;

import javax.faces.model.SelectItem;
import javax.faces.event.ValueChangeEvent;

/**
 * @author IShafigullin
 *
 */
public class Compendium {
	private Map<String, Section> sectionMap = new LinkedHashMap<String, Section>();
	private Section initialSection = null;
	private List<SelectItem> sectionItems = null;
	private String section = null;
	private String selectedSection = null;

	public Compendium() {
		Section generalDictionary = new Section();
		Section dictionaryForKK = new Section();

		initialSection = generalDictionary;
		//Общие справочники:
		generalDictionary.setDirectory("sections/generalDictionary");
		generalDictionary.setTitleKey("generalDictionary");
		generalDictionary.setTitleName("Общие справочники");
		generalDictionary
				.setImage("sections/generalDictionary/generalDictionary.jpg");
		//generalDictionary.setNumChapters(3);
		LinkedList<String> chapterKeys = new LinkedList<String>();
		chapterKeys.add("chapterWelcome");
		chapterKeys.add("departmentList");
		chapterKeys.add("userList");
		generalDictionary.setChapterKeys(chapterKeys);
		
		//Справочники кредитных комитетов:
		dictionaryForKK.setDirectory("sections/dictionaryForKK");
		dictionaryForKK.setTitleKey("dictionaryForKK");
		dictionaryForKK.setTitleName("Справочники для Кредитных комитетов");
		dictionaryForKK
				.setImage("sections/dictionaryForKK/dictionaryForKK.jpg");
		//dictionaryForKK.setNumChapters(4);
		chapterKeys = new LinkedList<String>();
		chapterKeys.add("chapterWelcome");
		//chapterKeys.add("userCrCoList");
		chapterKeys.add("questionList");
		dictionaryForKK.setChapterKeys(chapterKeys);		

		sectionMap.put("generalDictionary", generalDictionary);
		sectionMap.put("dictionaryForKK", dictionaryForKK);
	}

	public void setSection(String section) {
		this.section = section;
	}

	public String getSection() {
		return section;
	}

	public Map<String, Section> getSections() {
		return sectionMap;
	}

	public void sectionSelected(ValueChangeEvent e) {
		selectedSection = (String) e.getNewValue();
	}

	public Section getSelectedSection() {
		return selectedSection != null ? sectionMap.get(selectedSection)
				: initialSection;
	}

	public List<SelectItem> getSectionItems() {
		if (sectionItems == null) {
			sectionItems = new ArrayList<SelectItem>();
			Iterator<Section> it = sectionMap.values().iterator();
			while (it.hasNext()) {
				Section section = it.next();
				// sectionItems.add(new SelectItem(section.getTitleKey(),
				// getSectionTitle(section.getTitleKey())));
				sectionItems.add(new SelectItem(section.getTitleKey(), section
						.getTitleName()));

			}
		}
		return sectionItems;
	}

	@SuppressWarnings("unused")
	private String getSectionTitle(String key) {
		return com.corejsf.util.Messages.getString("com.corejsf.messages", key,
				null);
	}
}
