import 'package:flutter/material.dart';
import 'package:google_maps_flutter/google_maps_flutter.dart';
import 'package:geocoding/geocoding.dart';


class MapPage extends StatefulWidget {
  final String searchQuery;
  const MapPage({Key? key, required this.searchQuery}) : super(key: key);

  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MapPage> {
  late GoogleMapController mapController;
  final LatLng _center = const LatLng(45.521563, -122.677433);
  late LatLng _lastMapPosition = _center;
  Set<Marker> _markers = {};

  void _onMapCreated(GoogleMapController controller) {
    mapController = controller;
    searchLocation();
  }

  Future<void> searchLocation() async {
    // 검색결과 잘 전달됐는지 찍어보기
    print(widget.searchQuery);
    try {
      List<Location> locations = await locationFromAddress(widget.searchQuery);
      LatLng newPosition = LatLng(locations.first.latitude, locations.first.longitude);
      mapController.animateCamera(CameraUpdate.newLatLngZoom(newPosition, 11.0));
      setState(() {
        _markers.clear();
        _markers.add(Marker(
          markerId: MarkerId(newPosition.toString()),
          position: newPosition,
          infoWindow: InfoWindow(title: widget.searchQuery),
        ));
      });
    } catch (e) {
      print('Error finding location: $e');
    }
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Maps Sample App'),
          backgroundColor: Colors.green[700],
        ),
        body: GoogleMap(
          onMapCreated: _onMapCreated,
          markers: _markers,
          initialCameraPosition: CameraPosition(
            target: _center,
            zoom: 11.0,
          ),
        ),
      ),
    );
  }
}
