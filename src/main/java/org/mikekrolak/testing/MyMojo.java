package org.mikekrolak.testing;

import java.io.File;
import java.io.IOException;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

@Mojo(name = "LightningBug")
public class MyMojo extends AbstractMojo {
	@Parameter(defaultValue = "${project.build.directory}")
	private File sourceDirectory;

	public void execute() throws MojoExecutionException {
		getLog().info("Starting Static Code Analysis");	
	}
}
