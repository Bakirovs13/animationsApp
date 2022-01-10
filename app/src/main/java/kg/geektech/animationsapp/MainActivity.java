package kg.geektech.animationsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.sarnava.textwriter.TextWriter;

import java.util.Random;

import kg.geektech.animationsapp.databinding.ActivityMainBinding;
import me.wangyuwei.particleview.ParticleView;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        shineAnimation();
        LikeListeners();
        //textWriter();
        emitBubbles();
        particles();


    }

    private void particles() {
        binding.particleView.startAnim();
        binding.particleView.setOnParticleAnimListener(new ParticleView.ParticleAnimListener() {
            @Override
            public void onAnimationEnd() {
                Toast.makeText(MainActivity.this,
                        "Animation is End!!",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void emitBubbles()
    {
        handler.postDelayed(() -> {
            //determine the size of the bubbles and the range
            int size = new Random().nextInt(81)+20;
            binding.bubbleEmitter.emitBubble(size);
            emitBubbles();
        },
                //determine the range for the bubbles to appear
                new Random().nextInt(301)+100);
    }


    private void textWriter() {
        binding.textWriter
                .setWidth(8)
                .setDelay(30)
                .setColor(Color.YELLOW)
                .setConfig(TextWriter.Configuration.INTERMEDIATE)
                // set size of text
                .setSizeFactor(30f)
                .setLetterSpacing(15f)
                .setText("textwriter")
                .setListener(new TextWriter.Listener() {
                    @Override
                    public void WritingFinished() {
                        Toast.makeText(MainActivity.this,
                                "Learn Algorithm!",
                                Toast.LENGTH_SHORT).show();
                    }
                }).startAnimation();
    }

    private void LikeListeners() {
        binding.textViewAnimation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.textViewAnimation.isSelected()){
                    binding.textViewAnimation.setSelected(false);
                }else{
                    binding.textViewAnimation.setSelected(true);
                    binding.textViewAnimation.likeAnimation();
                }
            }
        });

        binding.imageViewAnimation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.imageViewAnimation.isSelected()){
                    binding.imageViewAnimation.setSelected(false);
                }else{
                    binding.imageViewAnimation.setSelected(true);
                    binding.imageViewAnimation.likeAnimation();
                }
            }
        });
    }

    private void shineAnimation() {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.left_right);
        binding.shine.startAnimation(anim);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                binding.shine.startAnimation(anim);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                binding.shine.startAnimation(anim);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                binding.shine.startAnimation(anim);
            }
        });

    }
}