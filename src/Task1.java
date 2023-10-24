public class Task1 {
    public static class Place {
        private final String name;
        private final double latitude;
        private final double longitude;
        private final String climat;

        public void Show() {
            System.out.println("Place`s name: " + this.name);
            System.out.println("Place`s latitude: " + this.latitude);
            System.out.println("Place`s longitude: " + this.longitude);
            System.out.println("Place`s climat: " + this.climat);
        }

        Place(String name, double latitude, double longitude, String climat) {
            this.name = name;
            this.latitude = latitude;
            this.longitude = longitude;
            this.climat = climat;
        }
    }

    public static class Region extends Place {
        private final double area;

        Region(String name, double latitude, double longitude, String climat, double area) {
            super(name, latitude, longitude, climat);
            this.area = area;
        }

        public void Show() {
            super.Show();
            System.out.println("Region`s Area in km^2: " + this.area + " km^2");
        }
    }

    public static class City extends Place {
        private final double population;

        City(String name, double latitude, double longitude, String climat, long population) {
            super(name, latitude, longitude, climat);
            this.population = population;
        }

        public void Show() {
            super.Show();
            System.out.println("City`s Population: " + this.population + " humans");
        }
    }

    public static class Metrocity extends City {
        private final double density_of_population;

        Metrocity(String name, double latitude, double longitude, String climat, long population,
                double density_of_population) {
            super(name, latitude, longitude, climat, population);
            this.density_of_population = density_of_population;
        }

        public void Show() {
            super.Show();
            System.out.println("Matrocity`s density of population: " + density_of_population + " persons/km²");
        }
    }

    public static void task1() {
        Place place_1 = new Place("Hoverla", 48.160, 24.500, "moderate");
        place_1.Show();
        System.out.println("------------------------------------------");

        Region region_1 = new Region("Chernivtsi region", 48.170, 25.560, "moderate", 8097);
        region_1.Show();
        System.out.println("------------------------------------------");

        City city_1 = new City("Chernivtsi", 48.170, 25.560, "moderate", 262276);
        city_1.Show();
        System.out.println("------------------------------------------");

        Metrocity metrocity_1 = new Metrocity("Kyiv", 50.270, 30.310, "moderate", 3133712, 3516.93);
        metrocity_1.Show();
        System.out.println("------------------------------------------");
    }
}
