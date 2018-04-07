//package com.example.wangmengyun.activity;
//
//        import android.os.Bundle;
//        import android.support.v7.app.AppCompatActivity;
//
//
//        import android.databinding.DataBindingUtil;
//
//        import com.example.wangmengyun.Utils.FackBoardingDataUtils;
//        import com.example.wangmengyun.lefei.databinding.ActivityBoardingpassBinding;
//
//
//        import com.example.wangmengyun.Utils.BoardingPassInfo;
//        import com.example.wangmengyun.lefei.R;
//        import android.databinding.DataBindingUtil;
//        import java.text.SimpleDateFormat;
//        import java.util.Locale;
//        import java.util.concurrent.TimeUnit;
//
//public class BoardingPassActivity extends AppCompatActivity {
//
//    ActivityBoardingpassBinding mBinding;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_boardingpass);
//
//        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_boardingpass);
//        BoardingPassInfo fakeBoardingInfo = FackBoardingDataUtils.generateFakeBoardingPassInfo();
//        displayBoardingPassInfo(fakeBoardingInfo);
//    }
//
//    private void displayBoardingPassInfo(BoardingPassInfo info) {
//
//        mBinding.textViewPassengerName.setText(info.passengerName);
//        // COMPLETED (7) Use the flightInfor attribute in mBinding below to get the appropiate text Views
////        mBinding.flightInfo.textViewOriginAirport.setText(info.originCode);
////        mBinding.flightInfo.textViewFlightCode.setText(info.flightCode);
////        mBinding.flightInfo.textViewDestinationAirport.setText(info.destCode);
//
//        SimpleDateFormat formatter = new SimpleDateFormat(getString(R.string.timeFormat), Locale.getDefault());
//        String boardingTime = formatter.format(info.boardingTime);
//        String departureTime = formatter.format(info.departureTime);
//        String arrivalTime = formatter.format(info.arrivalTime);
//
//        mBinding.textViewBoardingTime.setText(boardingTime);
//        mBinding.textViewDepartureTime.setText(departureTime);
//        mBinding.textViewArrivalTime.setText(arrivalTime);
//
//        long totalMinutesUntilBoarding = info.getMinutesUntilBoarding();
//        long hoursUntilBoarding = TimeUnit.MINUTES.toHours(totalMinutesUntilBoarding);
//        long minutesLessHoursUntilBoarding =
//                totalMinutesUntilBoarding - TimeUnit.HOURS.toMinutes(hoursUntilBoarding);
//
//        String hoursAndMinutesUntilBoarding = getString(R.string.countDownFormat,
//                hoursUntilBoarding,
//                minutesLessHoursUntilBoarding);
//
//        mBinding.textViewBoardingInCountdown.setText(hoursAndMinutesUntilBoarding);
//        // COMPLETED (8) Use the boardingInfo attribute in mBinding below to get the appropiate text Views
////        mBinding.boardingInfo.textViewTerminal.setText(info.departureTerminal);
////        mBinding.boardingInfo.textViewGate.setText(info.departureGate);
////        mBinding.boardingInfo.textViewSeat.setText(info.seatNumber);
//    }
//}
//
