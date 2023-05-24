import 'package:flutter/material.dart';
import 'package:google_maps_flutter/google_maps_flutter.dart';
import 'package:geocoding/geocoding.dart';

class MapData {
  String name;
  String address;
  double latitude;
  double longitude;
  String country;

  MapData({
    required this.name,
    required this.address,
    required this.latitude,
    required this.longitude,
    required this.country,
  });
}

class MapPage extends StatefulWidget {
  final String searchQuery;
  const MapPage({Key? key, required this.searchQuery}) : super(key: key);

  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MapPage> {
  MapData result = MapData(
    name: '',
    address: '',
    latitude: 0,
    longitude: 0,
    country: '',
  );
  late GoogleMapController mapController;
  final LatLng _center = const LatLng(45.521563, -122.677433);
  final Set<Marker> _markers = {};

  void _onMapCreated(GoogleMapController controller) {
    mapController = controller;
    searchLocation();
  }

  Future<void> searchLocation() async {
    try {
      List<Location> locations = await locationFromAddress(widget.searchQuery);
      LatLng newPosition =
          LatLng(locations.first.latitude, locations.first.longitude);
      List<Placemark> placemarks = await placemarkFromCoordinates(
          newPosition.latitude, newPosition.longitude);

      result.name = widget.searchQuery; //정확한 장소 이름이 반환이 안 됨, 검색어로 대신 처리
      result.address = placemarks.first.street.toString();
      result.latitude = locations.first.latitude;
      result.longitude = locations.first.longitude;
      result.country = placemarks.first.country.toString();

      //print("*****************************************");
      //print("${result.name}, ${result.address}, ${result.latitude}, ${result.longitude}, ${result.country},");

      mapController.animateCamera(CameraUpdate.newCameraPosition(
        CameraPosition(
          target: newPosition,
          zoom: 15.0,
        ),
      ));

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
        body: Stack(
          children: [
            GoogleMap(
              onMapCreated: _onMapCreated,
              initialCameraPosition: CameraPosition(
                target: _center,
                zoom: 10.0,
              ),
              markers: _markers,
            ),
            Positioned(
              bottom: 10.0,
              left: 10.0,
              right: 10.0,
              child: ElevatedButton(
                style: ElevatedButton.styleFrom(
                  backgroundColor: Color(0xFF9CC4FF),
                ),
                onPressed: () {
                  Navigator.pop(context);
                  Navigator.pop(context, result);
                },
                child: Text("확인"),
              ),
            ),
          ],
        ),
      ),
    );
  }
}
