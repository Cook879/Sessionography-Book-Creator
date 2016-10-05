
$( document ).ready( function () {

	var $id = $( '#modal-song-id' ).val();
	var $callerId = $( '#modal-song-caller' ).val();
	var $callerType = $( '#modal-song-caller-type' ).val();

	if ( $id != "" ) {
		$.getJSON( '/api/song/' + $id, function ( result ) {
			$( '#modal-song-title' ).val( result.title );
			if ( result.source != null ) {
				$( '#modal-song-source' ).val( result.source.name );
				$( '#modal-song-source-id' ).val( result.source.id );
			}
			if ( result.source2 != null ) {
				$( '#modal-song-source2' ).val( result.source2.name );
				$( '#modal-song-source2-id' ).val( result.source2.id );
			}
			$( '#modal-song-year' ).val( result.year );
			$( '#modal-song-notes' ).val( result.notes );
			$( '#modal-song-short-title' ).val( result.shortTitle );
		} );
	} else {
		$( '#modal-song-person' ).attr( "disabled", true );
	}

	$( '#btn-song-source-edit' ).click( function () {
		$( "#div-modal-source" ).load( 'source.html', function () {
			$( '#modal-source-id' ).val( $( '#modal-song-source-id' ).val() );
			$.getScript( 'js/source.js' );
			$( '#source-modal' ).modal( 'show' );
		} );
	} );
	$( '#btn-song-source-new' ).click( function () {
		$( "#div-modal-source" ).load( 'source.html', function () {
			$.getScript( 'js/source.js' );
			$( '#source-modal' ).modal( 'show' );
		} );
	} );
	$( '#btn-song-source2-edit' ).click( function () {
		$( "#div-modal-source" ).load( 'source.html', function () {
			$( '#modal-source-id' ).val( $( '#modal-song-source2-id' ).val() );
			$( '#modal-source-caller' ).val( "source2" );
			$.getScript( 'js/source.js' );
			$( '#source-modal' ).modal( 'show' );
		} );
	} );
	$( '#btn-song-source2-new' ).click( function () {
		$( "#div-modal-source" ).load( 'source.html', function () {
			$( '#modal-source-caller' ).val( "source2" );
			$.getScript( 'js/source.js' );
			$( '#source-modal' ).modal( 'show' );
		} );
	} );


	$( '#modal-song-person' ).click( function () {
		$( "#div-modal-song-person" ).load( 'song_person.html', function () {
			$( '#modal-song-person-id' ).val( $id );
			$.getScript( 'js/song_person.js' );
			$( '#song-person-modal' ).modal( 'show' );
		} );
	} );

	$( '#modal-song-submit' ).click( function () {

		var title = $( '#modal-song-title' ).val();
		var source = $( '#modal-song-source-id' ).val();
		var source2 = $( '#modal-song-source2-id' ).val();
		var year = $( '#modal-song-year' ).val();
		var notes = $( '#modal-song-notes' ).val();
		var short_title = $( '#modal-song-short-title' ).val();


		var id = $( '#modal-song-id' ).val();

		var object = {
			"title": title,
			"source": source,
			"source2": source2,
			"year": year,
			"notes": notes,
			"shortTitle": short_title
		};

		if ( id == "" ) {
			object[ 'id' ] = null;
			ajaxRequest( '/api/song', 'POST', object, $( '#modal-song-success' ), $( '#modal-song-error' ), processSubmit );
		} else {
			object[ 'id' ] = $id;
			ajaxRequest( '/api/song/' + $id, 'PUT', object, $( '#modal-song-success' ), $( '#modal-song-error' ), processSubmit );
		}
	} );

	function processSubmit( data ) {
		if ( $callerType == "medley" ) {
			$( '#modal-song-medley-id-' + $callerId ).val( data.id );
			$( '#modal-song-medley-title-' + $callerId ).val( data.shortTitle );
		} else {
			$( '#modal-session-song-song-id-' + $callerId ).val( data.id );
			$( '#modal-session-song-song-' + $callerId ).val( data.shortTitle );
		}
		$( "#modal-song-cancel" ).trigger( "click" );
	}

	$( '#modal-song-source' ).devbridgeAutocomplete( {
		serviceUrl: '/api/autocomplete/source',
		onSelect: function ( suggestion ) {
			$( '#modal-song-source-id' ).val( suggestion.data );
		}
	} );
	$( '#modal-song-source2' ).devbridgeAutocomplete( {
		serviceUrl: '/api/autocomplete/source',
		onSelect: function ( suggestion ) {
			$( '#modal-song-source2-id' ).val( suggestion.data );
		}
	} );

	$("#modal-song-source").on("change paste keyup", function() {
		if($(this).val() == "" ) {
			$("#modal-song-source-id").val("");
		}
	});	
	
	$("#modal-song-source2").on("change paste keyup", function() {
		if($(this).val() == "" ) {
			$("#modal-song-source2-id").val("");
		}
	});

} );

