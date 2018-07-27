package com.pragbits.bitbucketserver.tools;

import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class ChannelSelector {

	private String globalChannel;
	private String localChannel;
	private String selectedChannel;
	private Hashtable<String, String> fromBranchToChannelMap = new Hashtable<>();
	private Hashtable<String, String> toBranchToChannelMap = new Hashtable<>();
	private List<String> elems;

	public ChannelSelector(String globalChannel, String localChannel) {
		this.globalChannel = globalChannel;
		this.localChannel = localChannel;
		this.selectedChannel = "";
		setChannel();
	}

	public String getSelectedChannel() {
		return selectedChannel;
	}

	/**
	 * Returns the channel patterns for the "from" branch of a pull request.
	 *
	 * @return {@link Map}
	 */
	public Map<String, String> getFromBranchToChannelMap() {
		return fromBranchToChannelMap;
	}

	/**
	 * Returns the channel patterns for the "to" branch of a pull request.
	 *
	 * @return {@link Map}
	 */
	public Map<String, String> getToBranchToChannelMap() {
		return toBranchToChannelMap;
	}

	/**
	 * Either the selected channel is empty or the list of pattern to channel
	 *
	 * @return
	 */
	public boolean isEmptyOrSingleValue() {
		return selectedChannel.isEmpty() || (!elems.get(0).contains("->") && !elems.get(0).contains("<-"));
	}

	/**
	 * Sets the selected channel if possible and then populates the branch to channel maps if needed.
	 */
	private void setChannel() {
		if (!globalChannel.isEmpty()) {
			selectedChannel = globalChannel;
		}
		if (!localChannel.isEmpty()) {
			selectedChannel = localChannel;
		}

		elems = Arrays.asList(selectedChannel.split("\\s*,\\s*"));
		if (elems.get(0).contains("->") || elems.get(0).contains("<-")) {
			for (String elem : elems) {
				if (elem.contains("->")) {
					String[] pair = elem.split("\\s*->\\s*");
					fromBranchToChannelMap.put(pair[0], pair[1]);
				} else if (elem.contains("<-")) {
					String[] pair = elem.split("\\s*<-\\s*");
					toBranchToChannelMap.put(pair[0], pair[1]);
				}
			}
		}
	}
}
