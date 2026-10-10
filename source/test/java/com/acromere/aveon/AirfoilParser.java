package com.acromere.aveon;

import com.acromere.util.FileUtil;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AirfoilParser {

	@Test
	void testParse() throws Exception {
		String data = FileUtil.load( Paths.get("airfoils" ));
		//System.out.println( data );

		// <a href="/airfoil/details?airfoil=vr8b-il">vr8b-il - BOEING-VERTOL VR-8 AIRFOIL WITH TAB</a>
		Pattern pattern = Pattern.compile( "/airfoil/details\\?airfoil=([a-zA-Z0-9-]+)" );
		Matcher matcher = pattern.matcher( data );
		//System.out.println( matcher.find() );

		Set<String> ids = new HashSet<>();
		while (matcher.find() ) {
			String id = matcher.group(1);
			ids.add( id );
			System.out.println( id );
		}
		//System.out.println( ids.size() );
	}

}
