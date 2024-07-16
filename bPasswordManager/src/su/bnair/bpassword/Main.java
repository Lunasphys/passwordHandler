package su.bnair.bpassword;

import su.bnair.bpassword.utils.DatabaseUtils;
import su.bnair.bpassword.utils.bUtils;

import java.io.IOException;

public class Main {
	public static void main(String[] args) {
		try {
			bUtils.readConfig();
			DatabaseUtils.createInformationTable();
			DatabaseUtils.getEveryStoredInformations();
//			Instances.open(Instances.getLogin());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
