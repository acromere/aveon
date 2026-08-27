package com.acromere.aveon;

import com.acromere.xenon.Module;
import com.acromere.xenon.ToolRegistration;
import lombok.CustomLog;

@CustomLog
public class Aveon extends Module {

	private final AirfoilResourceType airfoilResourceType;

	private final FlowResourceType flowResourceType;

	public Aveon() {
		airfoilResourceType = new AirfoilResourceType( this );
		flowResourceType = new FlowResourceType( this );
	}

	@Override
	public void startup() {
		registerIcon( "airfoil", new FlowIcon() );
		registerIcon( "aveon", new FlowIcon() );
		registerIcon( "flow", new FlowIcon() );

		registerAction( this, "toggle-grid" );
		registerAction( this, "toggle-airfoil" );
//		registerAction( this, "toggle-reference-points" );
//		registerAction( this, "toggle-reference-lines" );
//		registerAction( this, "toggle-pressure-field" );
//		registerAction( this, "toggle-velocity-field" );
//		registerAction( this, "toggle-stream-field" );

		registerResourceType( airfoilResourceType );
		registerTool( airfoilResourceType, new ToolRegistration( this, AirfoilTool.class ) );

		registerResourceType( flowResourceType );
		registerTool( flowResourceType, new ToolRegistration( this, FlowTool.class ) );
	}

	@Override
	public void shutdown() {
		unregisterTool( flowResourceType, FlowTool.class );
		unregisterResourceType( flowResourceType );

		unregisterTool( airfoilResourceType, AirfoilTool.class );
		unregisterResourceType( airfoilResourceType );

//		unregisterAction( "toggle-stream-field" );
//		unregisterAction( "toggle-velocity-field" );
//		unregisterAction( "toggle-pressure-field" );
//		unregisterAction( "toggle-reference-lines" );
//		unregisterAction( "toggle-reference-points" );
		unregisterAction( "toggle-airfoil" );
		unregisterAction( "toggle-grid" );

		unregisterIcon( "flow", new FlowIcon() );
		unregisterIcon( "aveon", new FlowIcon() );
		unregisterIcon( "airfoil", new FlowIcon() );
	}

}
