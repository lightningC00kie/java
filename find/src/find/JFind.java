package find;
//package cz.cuni.mff.java.homework.jfind;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JFind {
	
	private static Map<String, String> argsMap = new HashMap<String, String>();
	private static String dir;
	public static void find(String[] args) {
		if(!dirIsValid(args[0])) {
			return;
		}
		dir = args[0];
		int i = 1;
		while (i < args.length) {
			if (argIsValid(args[i]) && i <= args.length - 1) {
				argsMap.put(args[i] , args[i+1]);
				i += 2;
			}
			else {
				System.out.print("ERROR");
				return;
			}
		}
		exploreDir(dir);
	}
	
	private static void exploreDir(String dir) {
		try {
			Files.find(Paths.get(dir), Integer.MAX_VALUE, (filePath, fileAttr) -> fileAttr.isRegularFile() && fitsCriteria(filePath)).
			forEach(p -> System.out.println(trimPath(p)));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static boolean fitsCriteria(Path p) {
		for(String key : argsMap.keySet()) {
			if (key.equals("-name") && !checkNameFormat(argsMap.get(key), p.getFileName().toString(), true)) {
				return false;
			}
			if (key.equals("-iname") && !checkNameFormat(argsMap.get(key), p.getFileName().toString(), false)) {
				return false;
			}
			if (key.equals("-regex") && !p.getFileName().toString().matches(argsMap.get(key))) {
				return false;
			}
			try {
				if (key.equals("-size") && validateSizeParam(argsMap.get(key)) && convertSize(argsMap.get(key)) != Files.size(p)) {
					System.out.println("made it here");
					return false;
				}
				if (key.equals("-ssize") && validateSizeParam(argsMap.get(key)) && convertSize(argsMap.get(key)) <= Files.size(p)) {
					return false;
				}
				if (key.equals("-bsize") && validateSizeParam(argsMap.get(key)) && convertSize(argsMap.get(key)) >= Files.size(p)) {
					return false;
				}
			}
			catch (IOException e) {
				System.out.println("ERROR");
			}
		}
		return true;
	}
	
	private static boolean dirIsValid(String dir) {
		File f = new File(dir);
		return !f.isDirectory() ? false : true;
	}
	
	private static boolean argIsValid(String arg) {
		return arg.equals("-name") || arg.equals("-iname") || arg.equals("-regex") || arg.equals("-size") || arg.equals("-ssize") || arg.equals("-bsize");
	}
	
	private static boolean validateSizeParam(String size) {
		Pattern pattern = Pattern.compile("^[\\d]+[kM]?$");
	    Matcher matcher = pattern.matcher(size);
	    return matcher.find();
	}
	
	private static boolean checkNameFormat(String regex, String name, boolean caseSensitive) {
		if (!caseSensitive) {
			name = name.toLowerCase();
			regex = regex.toLowerCase();
		}
	    return name.matches(convertRegex(regex));
	}
	
	private static int convertSize(String size) {
		if (size.endsWith("M")) {
			return Integer.parseInt(size.substring(0, size.length()-1)) * 1024 * 1024;
		}
		if (size.endsWith("k")) {
			return Integer.parseInt(size.substring(0, size.length()-1)) * 1024;
		}
		else {
			return Integer.parseInt(size);
		}
	}
	
	private static String convertRegex(String regex) {
		String convertedRegex = escapeDot(regex).replace("*", ".*");
		convertedRegex.replace("?", ".");
		return convertedRegex;
	}
	
	private static String escapeDot(String s) {
		return s.replace(".", "\\.");
	}
	
	private static String trimPath(Path p) {
		return p.toString().replace(dir + "/", "");
	}
}







