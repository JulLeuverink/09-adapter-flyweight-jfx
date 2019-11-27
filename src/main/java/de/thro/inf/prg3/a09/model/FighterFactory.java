package de.thro.inf.prg3.a09.model;

import de.thro.inf.prg3.a09.model.empire.TieBomber;
import de.thro.inf.prg3.a09.model.empire.TieFighter;
import de.thro.inf.prg3.a09.model.empire.TieInterceptor;
import de.thro.inf.prg3.a09.model.rebellion.AWing;
import de.thro.inf.prg3.a09.model.rebellion.XWing;
import de.thro.inf.prg3.a09.model.rebellion.YWing;
import de.thro.inf.prg3.a09.resource.FxImageLoaderAdapter;
import de.thro.inf.prg3.a09.resource.ResourceLoader;
import de.thro.inf.prg3.a09.util.NameGenerator;
import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Factory to create new fighters
 * Creates random fighters
 *
 * @author Peter Kurfer
 */
public final class FighterFactory {

	private static final int NumberOfKnownFighterTypes = 6;
	private final Random random;
	private final NameGenerator nameGenerator;
	//private final ResourceLoader<Image> imageResourceLoader;
	private final FxImageLoaderAdapter imageResourceLoaderAdapter;
	private final Map<String, Image> flyweights = new HashMap<>();

	public FighterFactory() {
		nameGenerator = new NameGenerator();
		random = new Random();
		//imageResourceLoader = new ResourceLoader<>(Image::new);
		imageResourceLoaderAdapter = new FxImageLoaderAdapter();
	}

	/**
	 * Create a random Fighter
	 *
	 * @implNote the method has an implementation flaw because it always loads the fighters image
	 * @return a new Fighter instance
	 */
	public Fighter createFighter() {
		String name = nameGenerator.generateName();
		int rnd = random.nextInt(NumberOfKnownFighterTypes);
		switch (rnd) {
			case 0:
				return new AWing(name, flyweight("fighter/awing.jpg"));
			case 1:
				return new XWing(name, flyweight("fighter/xwing.jpg"));
			case 2:
				return new YWing(name, flyweight("fighter/ywing.jpg"));
			case 3:
				return new TieBomber(name, flyweight("fighter/tiebomber.jpg"));
			case 4:
				return new TieFighter(name, flyweight("fighter/tiefighter.jpg"));
			default:
				return new TieInterceptor(name, flyweight("fighter/tieinterceptor.jpg"));
		}
	}

	private Image flyweight(String path){
		if (flyweights.containsKey(path)){
			return flyweights.get(path);
		}
		Image img = imageResourceLoaderAdapter.loadImage(path);
		flyweights.put(path, img);
		return img;
	}


}
