var $sourceType;

$( document ).ready( function () {

	getSourceTypes();

	var $id = $( '#modal-source-id' ).val();
	var $caller = $( '#modal-source-caller' ).val();


	if ( $id != "" ) {
		$.getJSON( '/api/source/' + $id, function ( result ) {
			$( '#modal-source-name' ).val( result.name );
			if ( result.studio != null ) {
				$( '#modal-source-studio' ).val( result.studio.name );
				$( '#modal-source-studio-id' ).val( result.studio.id );

			}
			$( '#modal-source-source-type' ).val( result.type );
			$sourceType = result.type;
			$( '#modal-source-year' ).val( result.year );
		} );
	}

	$( '#btn-source-studio-edit' ).click( function () {
		$( "#div-modal-studio" ).load( 'studio.html', function () {
			$( '#modal-studio-id' ).val( $( '#modal-source-studio-id' ).val() );
			$.getScript( 'js/studio.js' );
			$( '#studio-modal' ).modal( 'show' );
		} );
	} );
	$( '#btn-source-studio-new' ).click( function () {
		$( "#div-modal-studio" ).load( 'studio.html', function () {
			$.getScript( 'js/studio.js' );
			$( '#studio-modal' ).modal( 'show' );
		} );
	} );
	$( '#btn-source-type-edit' ).click( function () {
		$( "#div-modal-source-type" ).load( 'source_type.html', function () {
			$( '#modal-source-type-id' ).val( $( '#modal-source-source-type' ).val() );
			$.getScript( 'js/source_type.js' );
			$( '#source-type-modal' ).modal( 'show' );
		} );
	} );
	$( '#btn-source-type-new' ).click( function () {
		$( "#div-modal-source-type" ).load( 'source_type.html', function () {
			$.getScript( 'js/source_type.js' );
			$( '#source-type-modal' ).modal( 'show' );
		} );
	} );

	$( '#modal-source-submit' ).click( function () {
		var name = $( '#modal-source-name' ).val();
		var studio = $( '#modal-source-studio-id' ).val();
		var year = $( '#modal-source-year' ).val();
		var type = $( '#modal-source-source-type' ).val();

		var id = $( '#modal-source-id' ).val();

		var object = { "name": name, "studio": studio, "type": type, "year": year };

		if ( id == "" ) {
			object[ 'id' ] = null;
			ajaxRequest( '/api/source', 'POST', object, $( '#modal-source-success' ), $( '#modal-source-error' ), processSubmit );
		} else {
			object[ 'id' ] = $id;
			ajaxRequest( '/api/source/' + $id, 'PUT', object, $( '#modal-source-success' ), $( '#modal-source-error' ), processSubmit );
		}
	} );

	function processSubmit( data ) {
		if ( $caller == "source2" ) {
			$( '#modal-song-source2-id' ).val( data.id );
			$( '#modal-song-source2' ).val( data.name );
		} else {
			$( '#modal-song-source-id' ).val( data.id );
			$( '#modal-song-source' ).val( data.name );
		}
		$( "#modal-source-cancel" ).trigger( "click" );
	}


	$( '#modal-source-studio' ).devbridgeAutocomplete( {
		serviceUrl: '/api/autocomplete/studio',
		onSelect: function ( suggestion ) {
			$( '#modal-source-studio-id' ).val( suggestion.data );
		}
	} );
} );

function getSourceTypes( providedVal ) {
	var select = $( '#modal-source-source-type' );
	var selectVal = select.val();

	select.find( 'option' ).remove();

	$.getJSON( '/api/source_type', function ( result ) {
		$.each( result, function ( id, item ) {
			select.append( '<option value="' + item.id + '">' + item.name + '</option>' );
		} );

		select.val( selectVal );
		if ( selectVal == null ) {
			select.val( $sourceType );
		}

		if ( providedVal != null ) {
			select.val( providedVal );
		}
	} );
}