package com.northronics.tonemarks;

/**
 * Converts a string of pinyin with tone numbers into a string of pinyin with
 * tone marks.
 *
 * @author Silas Nordgren
 */
public final class TonemarkConverter {
	private TonemarkConverter() {

	}

	/**
	 * Converts the given string.
	 *
	 * @param str The string to convert.
	 * @return The converted string.
	 */
	public static String convert(String str) {
		if (str.contains(" ")) {
			String[] words = str.split(" ");
			StringBuilder toReturn = new StringBuilder();
			for (String word : words) {
				toReturn.append(convert(word));
			}
			return toReturn.toString();
		}

		if (str.equals("r5")) {
			return "r";
		}

		int tone = Integer.parseInt(str.substring(str.length() - 1, str.length())) - 1;
		int mainVowel = -1, replacement = -1;
		if (str.contains("a")) {
			mainVowel = 'a';
			replacement = "āāǎàa".charAt(tone);
		} else if (str.contains("o")) {
			mainVowel = 'o';
			replacement = "ōóǒòo".charAt(tone);
		} else if (str.contains("e")) {
			mainVowel = 'e';
			replacement = "ēéěèe".charAt(tone);
		} else if (str.contains("iu")) {
			mainVowel = 'u';
			replacement = "ūúǔùu".charAt(tone);
		} else if (str.contains("i")) {
			mainVowel = 'i';
			replacement = "īíǐìi".charAt(tone);
		} else if (str.contains("u")) {
			mainVowel = 'u';
			replacement = "ūúǔùu".charAt(tone);
		} else if (str.contains("v")) {
			mainVowel = 'v';
			replacement = "ǖǘǚǜü".charAt(tone);
		} else {
			throw new UnsupportedOperationException("No vowel found in " + str);
		}
		String x = new StringBuilder().appendCodePoint(mainVowel).toString();
		String y = new StringBuilder().appendCodePoint(replacement).toString();
		return str.replace(x, y).substring(0, str.length() - 1);
	}
}
