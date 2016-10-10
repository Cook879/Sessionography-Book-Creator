$( document ).ready( function () {

	var $id = $( '#modal-studio-id' ).val();
	var $caller = $( '#modal-studio-caller' ).val();

	if ( $id != "" ) {
		$.getJSON( '/api/studio/' + $id, function ( result ) {
			$( '#modal-studio-name' ).val( result.name );
		} );
	}

	$( '#modal-studio-submit' ).click( function () {

		var name = $( '#modal-studio-name' ).val();

		var id = $( '#modal-studio-id' ).val();

		var object = { "name": name };

		if ( id == "" ) {
			object[ 'id' ] = null;
			ajaxRequest( '/api/studio', 'POST', object, $( '#modal-studio-success' ), $( '#modal-studio-error' ), processSubmit );
		} else {
			object[ 'id' ] = $id;
			ajaxRequest( '/api/studio/' + $id, 'PUT', object, $( '#modal-studio-success' ), $( '#modal-studio-error' ), processSubmit );
		}
	} );

	function processSubmit( data ) {
		if ( $caller == "film" ) {
			$( '#modal-film-studio-id' ).val( data.id );
			$( '#modal-film-studio' ).val( data.name );
		} else if ( $caller == "radio-network" ) {
			$( '#modal-radio-network-id' ).val( data.id );
			$( '#modal-radio-network' ).val( data.name );
		} else if ( $caller == "radio-sponsor" ) {
			$( '#modal-radio-sponsor-id' ).val( data.id );
			$( '#modal-radio-sponsor' ).val( data.name );
		} else if ( $caller == "television" ) {
			$( '#modal-television-network-id' ).val( data.id );
			$( '#modal-television-network' ).val( data.name );
		} else {
			$( '#modal-source-studio-id' ).val( data.id );
			$( '#modal-source-studio' ).val( data.name );

		}
		$( "#modal-studio-cancel" ).trigger( "click" );
	}

} );


