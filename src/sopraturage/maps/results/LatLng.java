/*
 * Copyright 2014 Google Inc. All rights reserved.
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this
 * file except in compliance with the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under
 * the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF
 * ANY KIND, either express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package sopraturage.maps.results;

/**
 * A place on Earth, represented by a Latitude/Longitude pair.
 */
public class LatLng{

  /**
   * The latitude of this location.
   */
  public double lat;

  /**
   * The longitude of this location.
   */
  public double lng;

  /**
   * Construct a location with a latitude longitude pair.
   */
  public LatLng(double lat, double lng) {
    this.lat = lat;
    this.lng = lng;
  }
  
  @Override
  public String toString(){
	  return "Lat : " + this.lat + " | Lng : " + this.lng;
  }

}
