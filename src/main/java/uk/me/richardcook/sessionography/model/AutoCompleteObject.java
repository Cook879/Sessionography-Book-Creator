package uk.me.richardcook.sessionography.model;

import java.util.ArrayList;
import java.util.List;

public class AutoCompleteObject {

	private String query = "Unit";
	private List<ValueDataPair> suggestions = new ArrayList<ValueDataPair>();

	public String getQuery() {
		return query;
	}

	public void setQuery( String query ) {
		this.query = query;
	}

	public List<ValueDataPair> getSuggestions() {
		return suggestions;
	}

	public void setSuggestions( List<ValueDataPair> suggestions ) {
		this.suggestions = suggestions;
	}

	public void addValueDataPair( String value, String data ) {
		suggestions.add( new ValueDataPair( value, data ) );
	}

	private class ValueDataPair {
		private String value;

		private String data;

		private ValueDataPair( String value, String data ) {
			this.value = value;
			this.data = data;
		}

		public String getValue() {
			return value;
		}

		public void setValue( String value ) {
			this.value = value;
		}

		public String getData() {
			return data;
		}

		public void setData( String data ) {
			this.data = data;
		}
	}
}
