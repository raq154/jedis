package redis.clients.jedis;

import redis.clients.util.SafeEncoder;

import java.util.Arrays;

/**
 * Created by
 * Raheel Arif (raheelarifqayyum@gmail.com)
 * on 11/08/2015.
 */
public class GeoPositionTuple implements Comparable<GeoPositionTuple> {
    private byte[] element;
    private Double longitude;
    private Double latitude;

    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result;
        if (null != element) {
            for (final byte b : element) {
                result = prime * result + b;
            }
        }
        long temp;
        temp = Double.doubleToLongBits(longitude + latitude);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        GeoPositionTuple other = (GeoPositionTuple) obj;
        if (element == null) {
            if (other.element != null) return false;
        } else if (!Arrays.equals(element, other.element)) return false;
        return true;
    }

    public int compareTo(GeoPositionTuple other) {
        if (this.longitude.equals(other.longitude) || Arrays.equals(this.element, other.element) )
            return 0;
        else return this.latitude < other.latitude ? -1 : 1;
    }

    public GeoPositionTuple(String element, double latitude, double longitude) {
        super();
        this.element = SafeEncoder.encode(element);
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public GeoPositionTuple(byte[] element, double latitude, double longitude)  {
        super();
        this.element = element;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getElement() {
        if (null != element) {
            return SafeEncoder.encode(element);
        } else {
            return null;
        }
    }

    public byte[] getBinaryElement() {
        return element;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return  latitude;
    }

    public String toString() {
        return '[' + getElement() + ',' + latitude + + ',' +longitude+ ']';
    }
}
