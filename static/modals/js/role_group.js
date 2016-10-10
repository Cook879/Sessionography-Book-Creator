$( document ).ready( function () {

	var $id = $( '#modal-role-group-id' ).val();

	if ( $id != "" ) {
		$.getJSON( '/api/role_group/' + $id, function ( result ) {
			$( '#modal-role-group-name' ).val( result.name );
			$( '#modal-role-group-position' ).val( result.position );
			$( '#modal-role-group-arranger' ).prop( 'checked', result.arranger );
			$( '#modal-role-group-writer' ).prop( 'checked', result.writer );
			$( '#modal-role-group-group' ).prop( 'checked', result.group );
		} );
	}

	$( '#modal-role-group-submit' ).click( function () {

		var name = $( '#modal-role-group-name' ).val();
		var position = $( '#modal-role-group-position' ).val();
		var arranger = $( '#modal-role-group-arranger' ).prop( 'checked' );
		var writer = $( '#modal-role-group-writer' ).prop( 'checked' );
		var group = $( '#modal-role-group-group' ).prop( 'checked' );

		var id = $( '#modal-role-group-id' ).val();

		var object = { "name": name, "position": position, "arranger": arranger, "writer": writer, "group": group };

		if ( id == "" ) {
			object[ 'id' ] = null;
			ajaxRequest( '/api/role_group', 'POST', object, $( '#modal-role-group-success' ), $( '#modal-role-group-error' ), processSubmit );
		} else {
			object[ 'id' ] = $id;
			ajaxRequest( '/api/role_group/' + $id, 'PUT', object, $( '#modal-role-group-success' ), $( '#modal-role-group-error' ), processSubmit );
		}
	} );

	function processSubmit( data ) {
		getRoleGroups( data.id );
		$( '#modal-role-group' ).val( data.id );
		$( "#modal-role-group-cancel" ).trigger( "click" );
	}
} );


