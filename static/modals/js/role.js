
var $rollGroupValue;

$( document ).ready( function () {

	getRoleGroups();

	var $id = $( '#modal-role-id' ).val();
	var $callerId = $( '#modal-role-caller' ).val();
	var $type = $( '#modal-person-caller-type' ).val();


	if ( $id != "" ) {
		$.getJSON( '/api/role/' + $id, function ( result ) {
			$( '#modal-role-name' ).val( result.name );
			$( '#modal-role-abbreviation' ).val( result.abbreviation );
			$( '#modal-role-position' ).val( result.position );
			$( '#modal-role-role-group' ).val( result.group );
			$rollGroupValue = result.group;
		} );
	}

	$( '#btn-role-group-edit' ).click( function () {
		$( "#div-modal-role-group" ).load( 'role_group.html', function () {
			$( '#modal-role-group-id' ).val( $( '#modal-role-role-group' ).val() );
			$.getScript( 'js/role_group.js' );
			$( '#role-group-modal' ).modal( 'show' );
		} );
	} );
	$( '#btn-role-group-new' ).click( function () {
		$( "#div-modal-role-group" ).load( 'role_group.html', function () {
			$.getScript( 'js/role_group.js' );
			$( '#role-group-modal' ).modal( 'show' );
		} );
	} );

	$( '#modal-role-submit' ).click( function () {
		var name = $( '#modal-role-name' ).val();
		var abbreviation = $( '#modal-role-abbreviation' ).val();
		var position = $( '#modal-role-position' ).val();
		var group = $( '#modal-role-role-group' ).val();

		var id = $( '#modal-role-id' ).val();

		var object = { "name": name, "abbreviation": abbreviation, "position": position, "group": group };

		if ( id == "" ) {
			object[ 'id' ] = null;
			ajaxRequest( '/api/role', 'POST', object, $( '#modal-role-success' ), $( '#modal-role-error' ), processSubmit );
		} else {
			object[ 'id' ] = $id;
			ajaxRequest( '/api/role/' + $id, 'PUT', object, $( '#modal-role-success' ), $( '#modal-role-error' ), processSubmit );
		}
	} );

	function processSubmit( data ) {
		if ( $type == "song" ) {
			$( '#modal-song-person-role-id-' + $callerId ).val( data.id );
			$( '#modal-song-person-role-' + $callerId ).val( data.name );
		} else {
			$( '#modal-session-song-person-role-id-' + $callerId ).val( data.id );
			$( '#modal-session-song-person-role-' + $callerId ).val( data.name );
		}
		$( '#modal-role-cancel' ).trigger( "click" );
	}
} );

function getRoleGroups( providedVal ) {
	// Get a list of role_groups
	var select = $( '#modal-role-role-group' );
	var selectVal = select.val();

	select.find( 'option' ).remove();

	$.getJSON( '/api/role_group', function ( result ) {
		$.each( result, function ( id, item ) {
			select.append( '<option value="' + item.id + '">' + item.name + '</option>' );
		} );

		select.val( selectVal );
		if ( selectVal == null ) {
			select.val( $rollGroupValue );
		}

		if ( providedVal != null ) {
			select.val( providedVal );
		}
	} );
}

