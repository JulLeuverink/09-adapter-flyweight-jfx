package de.thro.inf.prg3.a09.resource;

import javafx.scene.image.Image;

public class FxImageLoaderAdapter {
	private ResourceLoader<Image> resourceLoader;

	public FxImageLoaderAdapter(){
		resourceLoader = new ResourceLoader<>(Image::new);
	}

	public Image loadImage(String resourcePath){
		return resourceLoader.loadResource(ClassLoader.getSystemClassLoader(), resourcePath);
	}

	public Image loadImage(ClassLoader resourceContext, String path){
		return resourceLoader.loadResource(resourceContext, path);
	}
}
