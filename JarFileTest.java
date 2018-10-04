import java.util.jar.*;
import java.io.*;

public class JarFileTest {
	public static void main(final String[] args) throws Exception {
		final String tmpDir = System.getProperty("java.io.tmpdir");
		try {
			final JarFile jarFile = new JarFile(tmpDir + File.separator + "*");
		} catch (IOException ioe) {
			System.out.println("Got the expected IOException " + ioe);
		}
	}
}