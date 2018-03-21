package com.example.wangmengyun.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;


    public class MyLetterView extends View {

        private Paint mPaint;
        private boolean isShowBg = false;// ���������Ƿ���ʾview�ı���
        private OnSlidingListener mOnSlidingListener;// ������View�ļ�����
        private int choose = -1;// ���ڱ�ǵ�ǰ��ѡ�е�λ��
        private TextView mTvDialog;//���ڽ��ܴ�activity�д������ģ��м�����չʾ��ĸ��textView
        //��Ҫչʾ������
        private String[] letter = { "��λ", "���", "����", "ȫ��", "A", "B", "C", "D",
                "E", "F", "G", "H","J", "K", "L", "M", "N","P", "Q",
                "R", "S", "T","W", "X", "Y", "Z" };

        public MyLetterView(Context context) {
            super(context);

        }

        public MyLetterView(Context context, AttributeSet attrs) {
            super(context, attrs);
            initPaint();
        }

        public MyLetterView(Context context, AttributeSet attrs, int defStyle) {
            super(context, attrs, defStyle);

        }

        private void initPaint() {
            mPaint = new Paint();
            mPaint.setAntiAlias(true);
            mPaint.setTextSize(26);
            mPaint.setColor(Color.parseColor("#8c8c8c"));
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            //����View������ʱ����ʾ�ı�����ɫ
            if (isShowBg) {
                canvas.drawColor(Color.parseColor("#40000000"));
            }
            //����ÿ���ַ���ռ�ĸ߶�
            float singleHeight = getHeight() / letter.length;
            int width = getWidth();
            for (int i = 0; i < letter.length; i++) {
                String text = letter[i];

                float xPosition = width / 2 - mPaint.measureText(text) / 2;
                float yPosition = singleHeight * i + singleHeight;
                //ͨ�����ϵĸı�yPosition�������е�����һ��һ�����Ƶ��Զ����View��
                canvas.drawText(text, xPosition, yPosition, mPaint);
            }
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {

            int action = event.getAction();
            int position = (int) (event.getY() / getHeight() * letter.length);
            int oldChoose = choose;
            switch (action) {
                case MotionEvent.ACTION_DOWN:

                    isShowBg = true;
                    if (oldChoose != position && mOnSlidingListener != null) {
                        if (position > 0 && position < letter.length) {
                            //������������ĸ���ݵ�activity��
                            mOnSlidingListener.sliding(letter[position]);
                            choose=position;
                            if(mTvDialog!=null){
                                mTvDialog.setVisibility(View.VISIBLE);
                                mTvDialog.setText(letter[position]);
                            }
                        }
                        invalidate();
                    }
                    break;

                case MotionEvent.ACTION_MOVE:

                    isShowBg = true;
                    if (oldChoose != position && mOnSlidingListener != null) {
                        if (position >=0 && position < letter.length) {
                            mOnSlidingListener.sliding(letter[position]);
                            choose=position;
                            if(mTvDialog!=null){
                                mTvDialog.setVisibility(View.VISIBLE);
                                mTvDialog.setText(letter[position]);
                            }
                        }
                        invalidate();
                    }
                    break;

                case MotionEvent.ACTION_UP:
                    isShowBg = false;
                    choose=-1;
                    if(mTvDialog!=null){
                        mTvDialog.setVisibility(View.GONE);
                    }
                    invalidate();
                    break;
            }

            return true;
        }
        //MyLetterView��һ�������ļ���
        public void setOnSlidingListener(OnSlidingListener mOnSlidingListener) {
            this.mOnSlidingListener = mOnSlidingListener;
        }

        public interface OnSlidingListener {
            public void sliding(String str);
        }

        public void setTextView(TextView tvDialog) {
            mTvDialog=tvDialog;
        }

    }

