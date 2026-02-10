package com.acromere.aveon;

import com.acromere.util.TextUtil;
import com.acromere.xenon.Xenon;
import com.acromere.xenon.XenonProgramProduct;
import com.acromere.xenon.resource.Resource;
import com.acromere.xenon.resource.exception.ResourceException;
import com.acromere.xenon.resource.ResourceType;
import javafx.scene.control.TextInputDialog;
import lombok.CustomLog;

import java.net.URI;
import java.net.URISyntaxException;

@CustomLog
public class AirfoilResourceType extends ResourceType {

	public AirfoilResourceType( XenonProgramProduct product ) {
		super( product, "airfoil2d" );
		setDefaultCodec( new AirfoilStationPointCodec() );
	}

	@Override
	public String getKey() {
		return getDefaultCodec().getKey();
	}

	@Override
	public boolean assetOpen( Xenon program, Resource resource ) throws ResourceException {
		Airfoil airfoil = new Airfoil();
		resource.setModel( airfoil );
		return true;
	}

	@Override
	public boolean assetNew( Xenon program, Resource resource ) throws ResourceException {
		try {
			String url = requestAirfoilData( "http://airfoiltools.com/airfoil/lednicerdatfile?airfoil=e376-il" );
			if( TextUtil.isEmpty( url ) ) return false;
			resource.setUri( new URI( url.replace( " ", "%20" ) ) );
		} catch( URISyntaxException exception ) {
			throw new ResourceException( resource, exception );
		}
		return true;
	}

	private String requestAirfoilData( String url ) {
		TextInputDialog dialog = new TextInputDialog( url );
		dialog.initOwner( getProgram().getWorkspaceManager().getActiveStage() );
		dialog.setGraphic( getProgram().getIconLibrary().getIcon( "flow" ) );
		dialog.setTitle( "Airfoil" );
		dialog.setHeaderText( "Choose an airfoil..." );
		dialog.setContentText( "URL:" );
		return dialog.showAndWait().orElse( "" );
	}

}
