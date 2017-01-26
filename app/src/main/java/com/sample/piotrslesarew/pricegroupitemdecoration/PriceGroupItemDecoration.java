package com.sample.piotrslesarew.pricegroupitemdecoration;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

class PriceGroupItemDecoration extends RecyclerView.ItemDecoration {

    private HeaderAdapter adapter;
    private Paint paint = new Paint();

    PriceGroupItemDecoration(HeaderAdapter adapter) {
        this.adapter = adapter;
        paint.setColor(android.graphics.Color.GRAY);
        paint.setStrokeWidth(6f);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view);
        View header = getHeader(parent, position).itemView;
        outRect.set(0, header.getHeight(), 0, 0);
    }

    @Override
    public void onDrawOver(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
        int count = parent.getChildCount();
        long previousHeaderId = 0;

        for (int layoutPos = 0; layoutPos < count; layoutPos++) {
            View child = parent.getChildAt(layoutPos);
            int adapterPos = parent.getChildAdapterPosition(child);
            long headerId = adapter.getHeaderId(adapterPos);
            View header = getHeader(parent, adapterPos).itemView;

            float headerHeight = header.getHeight();
            float headerRight = header.getRight();
            float childRight = child.getRight();

            canvas.save();

            if (headerId == previousHeaderId) {
                drawGroupLine(canvas, child, header);
                drawGroupEndingLineIfNeeded(canvas, childRight, count, headerHeight, headerId, layoutPos, parent);
            } else {
                previousHeaderId = headerId;

                float top = child.getY() - header.getHeight();
                float left = getHeaderLeft(child, childRight, count, header, headerId, layoutPos, parent);

                canvas.translate(left, top);
                header.draw(canvas);

                if (childRight > header.getWidth()) {
                    canvas.drawLine(headerRight, headerHeight / 2, childRight, headerHeight / 2, paint);
                    drawGroupEndingLineIfNeeded(canvas, childRight, count, headerHeight, headerId, layoutPos, parent);
                }

            }

            canvas.restore();
        }
    }

    private RecyclerView.ViewHolder getHeader(RecyclerView parent, int position) {
        final RecyclerView.ViewHolder holder = adapter.onCreateHeaderViewHolder(parent);
        final View header = holder.itemView;

        //noinspection unchecked
        adapter.onBindHeaderViewHolder(holder, position);

        int widthSpec = View.MeasureSpec.makeMeasureSpec(parent.getMeasuredWidth(), View.MeasureSpec.EXACTLY);
        int heightSpec = View.MeasureSpec.makeMeasureSpec(parent.getMeasuredHeight(), View.MeasureSpec.UNSPECIFIED);

        int childWidth = ViewGroup.getChildMeasureSpec(widthSpec,
                parent.getPaddingLeft() + parent.getPaddingRight(), header.getLayoutParams().width);
        int childHeight = ViewGroup.getChildMeasureSpec(heightSpec,
                parent.getPaddingTop() + parent.getPaddingBottom(), header.getLayoutParams().height);

        header.measure(childWidth, childHeight);
        header.layout(0, 0, header.getMeasuredWidth(), header.getMeasuredHeight());

        return holder;
    }

    private void drawGroupLine(Canvas canvas, View child, View header) {
        float startX = child.getLeft() < header.getWidth() ? header.getRight() : child.getLeft();
        canvas.drawLine(startX,
                        header.getHeight() / 2,
                        child.getRight(),
                        header.getHeight() / 2,
                        paint);
    }

    private void drawGroupEndingLineIfNeeded(Canvas canvas,
                                             Float childRight,
                                             int count,
                                             float headerHeight,
                                             long headerId,
                                             int layoutPos,
                                             RecyclerView parent) {
        if (isNotLastItem(count, layoutPos) && isNextItemFromDifferentGroup(headerId, layoutPos, parent)) {
            canvas.drawLine(childRight - paint.getStrokeWidth() / 2,
                            headerHeight / 2,
                            childRight - paint.getStrokeWidth() / 2,
                            headerHeight / 2 + 20, paint);
        }
    }

    private float getHeaderLeft(View child,
                                float childRight,
                                int count,
                                View header,
                                long headerId,
                                int layoutPos,
                                RecyclerView parent) {
        if (isPricePushingNeeded(childRight, count, header, headerId, layoutPos, parent)) {
            return child.getX() + child.getWidth() - header.getWidth();
        } else {
            return child.getX() < 0 ? 0F : child.getX();
        }
    }

    private boolean isPricePushingNeeded(float childRight,
                                         int count,
                                         View header,
                                         long headerId,
                                         int layoutPos,
                                         RecyclerView parent) {
        return childRight < header.getWidth()
                && isNotLastItem(count, layoutPos)
                && isNextItemFromDifferentGroup(headerId, layoutPos, parent);
    }

    private boolean isNotLastItem(int count, int layoutPos) {
        return layoutPos + 1 <= count - 1;
    }

    private boolean isNextItemFromDifferentGroup(long headerId, int layoutPos, RecyclerView parent) {
        return headerId != getNextHeaderId(layoutPos, parent);
    }

    private long getNextHeaderId(int layoutPos, RecyclerView parent) {
        View nextChild = parent.getChildAt(layoutPos + 1);
        int nextAdapterPos = parent.getChildAdapterPosition(nextChild);
        return adapter.getHeaderId(nextAdapterPos);
    }

    interface HeaderAdapter<T extends RecyclerView.ViewHolder> {

        long getHeaderId(int position);
        T onCreateHeaderViewHolder(ViewGroup parent);
        void onBindHeaderViewHolder(T viewHolder, int position);
    }
}
