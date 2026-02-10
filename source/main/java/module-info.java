import com.acromere.aveon.Aveon;
import com.acromere.xenon.Module;

module com.acromere.aveon {

	// Compile-time only
	requires static lombok;

	// Both compile-time and run-time
	requires com.acromere.curve;
	requires com.acromere.marea;
	requires com.acromere.xenon;
	requires com.acromere.zenna;
	requires com.acromere.zerra;
	requires com.acromere.zevra;
	requires javafx.controls;
	requires javafx.graphics;

	opens com.acromere.aveon.bundles;

	exports com.acromere.aveon to com.acromere.xenon, com.acromere.zerra;
	exports com.acromere.geometry;

	provides Module with Aveon;

}
