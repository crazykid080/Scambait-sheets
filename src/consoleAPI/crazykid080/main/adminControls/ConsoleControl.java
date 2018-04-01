package consoleAPI.crazykid080.main.adminControls;

import java.util.ArrayList;

public class ConsoleControl{
	protected static ArrayList<Object> consoles = new ArrayList<>();

	public static void registerConsole(Object hash){
		consoles.add(hash);
	}

	public static ArrayList<Object> getConsoles(){
		return consoles;
	}
	public static void write(String text){
		System.out.println(text);
		for (Object object : consoles) {
			((AdminConsole)object).write(text);
		}
	}
	public static void write(String text, LogLevels level){
		System.out.println(text);
		for (Object object : consoles) {
			((AdminConsole)object).write(text , level);
		}
	}

	public static void createError(String text, LogLevels level, String className){
		String fullError = "ERROR ( "+ className + " )" + text;
		write(fullError, level);
	}

	public static void write(int i) {
		System.out.println(i);
		String a = String.valueOf(i);
		for (Object object : consoles) {
			((AdminConsole)object).write(a);
		}
	}


}
