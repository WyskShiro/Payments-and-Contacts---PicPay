package will.shiro.desafiopicpay.util.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import will.shiro.desafiopicpay.databinding.CustomMoneyInputViewBinding;
import will.shiro.desafiopicpay.util.mask.MoneyMask;

public class MoneyInputLayout extends ConstraintLayout {

    final CustomMoneyInputViewBinding binding = CustomMoneyInputViewBinding.inflate(
            LayoutInflater.from(getContext()),
            this,
            true
    );
    public EditText editText = null;
    public TextView textView = null;

    public MoneyInputLayout(Context context) {
        super(context);
        init();
    }

    public MoneyInputLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    private void init() {
        editText = binding.editText;
        textView = binding.textView;
        editText.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setSelection(editText.getText().length());
            }
        });
        editText.addTextChangedListener(new MoneyMask(editText, textView));
    }
}
