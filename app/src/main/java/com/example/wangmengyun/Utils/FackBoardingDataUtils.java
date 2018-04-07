//package com.example.wangmengyun.Utils;
//
//import com.example.wangmengyun.lefei.R;
//
//import java.security.Timestamp;
//import java.util.concurrent.TimeUnit;
//
///**
// * Created by wangmengyun on 2018/4/4.
// */
//
//public class FackBoardingDataUtils {
//    public static BoardingPassInfo generateFakeBoardingPassInfo() {
//
//        BoardingPassInfo bpi = new BoardingPassInfo();
//
//        bpi.passengerName = "MR. RANDOM PERSON";
//        bpi.flightCode = "UD 777";
//        bpi.originCode = "JFK";
//        bpi.destCode = "DCA";
//
//        long now = System.currentTimeMillis();
//
//        // Anything from 0 minutes up to (but not including) 30 minutes
//        long randomMinutesUntilBoarding = (long) (Math.random() * 30);
//        // Standard 40 minute boarding time
//        long totalBoardingMinutes = 40;
//        // Anything from 1 hours up to (but not including) 6 hours
//        long randomFlightLengthHours = (long) (Math.random() * 5 + 1);
//
//        long boardingMillis = now + minutesToMillis(randomMinutesUntilBoarding);
//        long departure = boardingMillis + minutesToMillis(totalBoardingMinutes);
//        long arrival = departure + hoursToMillis(randomFlightLengthHours);
////
////        bpi.boardingTime = new Timestamp(boardingMillis);
////        bpi.departureTime = new Timestamp(departure);
////        bpi.arrivalTime = new Timestamp(arrival);
//        bpi.departureTerminal = "3A";
//        bpi.departureGate = "33C";
//        bpi.seatNumber = "1A";
//        bpi.barCodeImageResource = R.drawable.art_plane;
//
//        return bpi;
//    }
//
//    private static long minutesToMillis(long minutes) {
//        return TimeUnit.MINUTES.toMillis(minutes);
//    }
//
//    private static long hoursToMillis(long hours) {
//        return TimeUnit.HOURS.toMillis(hours);
//    }
//}
