package rs.novisad.crimetime.entity;

public class ConvertText {

	public static String convert(String text) {
		text = text.replace("š", "s").
		replace("Š", "s").
		replace("Ž", "z").
		replace("ž", "z").
		replace("Ć", "c").
		replace("Č", "c").
		replace("č", "c").
		replace("ć", "c").
		replace("Đ", "dj").
		replace("đ", "dj");
		return text;
	}
}
