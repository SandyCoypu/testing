package org.mikekrolak.testing;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import org.apache.commons.io.FileUtils;
import org.eclipse.jgit.api.BlameCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.blame.BlameResult;
import org.eclipse.jgit.diff.RawText;
import org.eclipse.jgit.lib.PersonIdent;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;

public class JGitTests {
	public static void main(String[] args) {
		test();
	}

	public static Repository openRepository() throws IOException {
	//	FileRepositoryBuilder builder = new FileRepositoryBuilder();
	//	return builder.readEnvironment().findGitDir().build();
//		return Git.open(FileUtils.getFile(".git")).checkout().getRepository();
		return new FileRepositoryBuilder().setGitDir(new File("C:\\Users\\ukrolmi\\eclipse-workspace\\testing\\.git")).build();
	}

	public static void test() {
		try (Repository repo = openRepository()) {
			final String[] list = new File(".").list();
			Collection<File> files = FileUtils.listFiles(FileUtils.getFile("."), new String[] { "java" }, true);
			for (String s : list) {
				System.out.println(s);
			}
			if (list == null) {
				throw new IllegalStateException("Did not find any files at " + new File(".").getAbsolutePath());
			}

			for (File file : files) {
				if (!(file.isDirectory())) {
					System.out.println("Blaming " + file);
					
					BlameCommand b = new Git(repo).blame().setFilePath(file.getPath());
					System.out.println(b);

					final BlameResult result = Git.setRemote("https://github.com/SandyCoypu/testing.git").blame().setFilePath(file.getPath()).call();
					final RawText rawText = result.getResultContents();
					for (int i = 0; i < rawText.size(); i++) {
						final PersonIdent sourceAuthor = result.getSourceAuthor(i);
						final RevCommit sourceCommit = result.getSourceCommit(i);
						System.out.println(sourceAuthor.getName() + (sourceCommit != null
								? "/" + sourceCommit.getCommitTime() + "/" + sourceCommit.getName()
								: "") + ": " + rawText.getString(i));
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (GitAPIException ex) {
			ex.printStackTrace();

		}
	}

}
