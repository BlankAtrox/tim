package com.example.design;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.design.Interfaces.TelemedServ;
import com.example.design.s.Users;
import com.google.gson.annotations.SerializedName;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class register extends AppCompatActivity {

    private TextView a1, a2, a3, a4, a5, a6, a7, a8;
    private Button photo, reg;
    Uri imageUri;
    private TelemedServ telemedServ;
    public String encoded;

    String fullname;
    String birthday;
    String email;
    String phone;
    String adress;
    String username;
    String password;
    String base64photo;
    String status;

    Intent intent1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.abs_layout);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        intent1 = new Intent(this, login.class);

        a1 = findViewById(R.id.textView11);
        a2 = findViewById(R.id.textView14);
        a3 = findViewById(R.id.textView17);
        a4 = findViewById(R.id.textView20);
        a5 = findViewById(R.id.textView23);
        a6 = findViewById(R.id.textView8);
        a7 = findViewById(R.id.textView27);

        reg = findViewById(R.id.button22);

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fullname = a1.getText().toString();
                birthday = a2.getText().toString();
                email = a3.getText().toString();
                phone = a4.getText().toString();
                adress = a5.getText().toString();
                password = a6.getText().toString();
                username = a7.getText().toString();
                base64photo = encoded;
                inregistrare();
                startActivity(intent1);
            }
        });

        photo = findViewById(R.id.button4);
        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent gallery = new Intent();
                gallery.setType("image/*");
                gallery.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(Intent.createChooser(gallery, ""), 1);

            }
        });

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://81.180.72.17/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        telemedServ = retrofit.create(TelemedServ.class);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            imageUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                Drawable d = new BitmapDrawable(getResources(), bitmap);
                photo.setBackground(d);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                encoded = Base64.encodeToString(byteArray, Base64.DEFAULT);
                encoded = encoded.replace("\n","");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void inregistrare() {

        Users users = new Users(fullname, birthday, email, phone, adress, username, password, base64photo);
        //Users users = new Users("Vasia Vas","03-04-1900", "deniskarusu1913@mail.ru", "068192325", "Home", "vasia25", "vasia25", "iVBORw0KGgoAAAANSUhEUgAAAEAAAABACAYAAACqaXHeAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsMAAA7DAcdvqGQAABqhSURBVHhedZt5cxzHkcXxGTfslUTimhlgbgxOQgQPgDgIEiBAgocoWZJDu7EOR1iyKVE8xRO8T1HS2mvHbmzEfpTa98vqN2zC1B8Z3V1d3VPv5cusrEJjYGThz6mxfDX1Tt5OM1v30+Jnr9PmH/6R1v/972nq1G4aOviXVF++nubPPk1LX/ycupv3Un3tZhpbuZ4a67fT+NqtVDl2PQ0evhzH1sb9NLnzJDU37qXKyo3UOHk31dZ+SCPHrqXx9Tupusq5+m3u6t493buVhpeuxbF96kGaPPM8NU8+SMOLN9LgEbWv3ulfjyzdjOvqyu00tnYvNU7sxjX3ho7eSJ1Tj1N9XWM+ej3tO3IjfXj4Wvpg4Wr64NDVuB5c+iENLen3lm+n0ZW7qbr2IA00V66lzvGbqbv+Q2qv3Uiz27si4VU69sVPaXLzfho9/F2aEIjZ04/CxldvpOqxq2EVgaou61o2sng1jQlcRyDaeg7Qg0e/DwN8dfWmSLmf6icg5FYYZFRWbqZR3qHz8eN3BOBeAARsZVl9dM4Ro230GP3khPX7IuFuXEMAx6mdF0ECfYeO3Ur7RArgIYJz2kZX76aKyAtb3U0DAMb7qKBy+FIaX/peZNxK01u7qSlP15aupAPy6NyZx6kjjw8d/jYNHrqURhe/T0NHBE7Aa/I04NsCOHXmiTx5P7z/0aFv028OfJ32H7ks70stUgAEYIBGCRwhAyJGlq7Lsqezl+8GuH2HrgRggHFOO54fXZZHF6+rz/Xoj3ogIO5LCSMrd+T1m2m/3rF/8WZ4vnpczkE5uj+6ej8NTG7cCdCEAsfW6vXUPi7prl5Lo0cupRGRsnDhhRTxc1r45GUaPvJdgG9v3E0TIqm9eVdSvJ16px+k6bOP0+z5p2n+01eS+P0g4MOFS6EG5D0kspA7RFj+JgEF1NZuCxh2p2/I+aOF78PrePnDg5f13stpn0Ju8OiV/M7Fa3EcPHpV977Tb34n7+p5qamydkfgFRJHr6XBY1KQVFY7kUlADQNTAoD8AY4KOHZP/JCmt++nloioLl6WGh6ECqZ0HDl6OdWWFTbKBYC3dZU/mlJSXe+aVr4gBwwtXgn510/cEQG7AZ6B4m1IgACu90tJENDc2BVQSVSexvMc8SbeBzxkkBcACnFjx7OqTCTgPzh4KX2oY/3kbuqeeZY6p59KBVLjEf0O6hLJ1RP30pjuV6SGgWkN/sCZh2l+51HkAAiAlMXPX6fZMw/S+PKV1JIHSYRVxTOxTxJsyesVEQbgCYUR4EePXUn7FSL1kLvuKww4QgBHJ0CIcOxDwIcL38Y1ibEmjwF+/2E8e0PEKvwu/BjngK9I9pUVjUdh1N1+lDpbD0NNvJv3hip0BPjcp2/S7MU3kv7NggCF2KrCbf1uGtt4IBIepoG6ZA/gMgHTW/fS8pdvUm9TMfTx15EEIWBQMwKzwIRinGT4wfyfJFP1l/RRASRgFchS0jMJw0vI8/vwFF4zERAAcEjIEtbgJVU87+wOAQc/+yUyflbBtQgBwOZ33Yx3ExIkWRTBO5unHqap8y9kClsRMKj+I/yuCIgwOHk/NbYepYFxSRzQ5ALPBITA/NlHqQsYxXxTPzImEBX9UEdktE/eiWlw36G/hArIASOSO9Ni7/RDAcsE4HkIMBkMsKckOXX2WXgQEgCNAWjkGNOZZgwltNbGwzAIwHLiy+TRn7zSUUgyzfJ7hNrC5z+nGYGG6JqIaAlg98zTIGBI766IsGERgEFCEEDck/yaAjSpxAYJDU2NLRHREbA2XlQWrwIQUICBEPWvyirKB0PKC/86903MBNM7T2OQgB47rh/S4MgHU8oLJMLZCy/ToS9+SROnH0c/PIehhnFJk4w+pjDobj9JM+deSVmPiplB95UfyCW8m+PcJ6+CTN4P2Sv/8d9BQl0xXhEJ4/Jya+uxCJDnVyR9PT8oMvaLjGFd10+JALxLjHcFFKn3yO46jivW22Ksp7isyDujIqAmj1VlY5JYQ+Da+uEWffV8VS9tKbtOiXHP9YDKyepWDJJrZAsRGGqYFGGWP31JiGNKVMQ3imFmQBkYBJmA7vbDdPB3PwUJkJmnVxLzI733WeoqB7S3n4qAJ8WUd1cq+CHXBcolwwqzuvoOhHclbWK8IbkCngKoqgE15aHJTSVCHSEBAzwkjGrQLQYklpuS27gYbeAhZXIXPMQjQDkCDqBZ6khWuePc87T41X9FX4jJSfDdAol+PMOztDmhQgQkeSYgETbkAHJFXdYUuJpmj+FlVY7H5cS1+yLgtooizRZHNBYVRRAzsI9CZf6bNKI5uyYvt+WtHt46rLlXrHcEsqEfqZGwNBDActx38Ns0JmnVFZcNSZbjmEgYU3xBAAb4jz97E0emKHua89/O/zkUcPyP/xthgZQhhKwOUKYz1EB/PwcxAVROcngxg3AfEjuSNPUCybOqSo/i57cHVeMsa2pV1Te6ck/J8FaUxBiF0gDeBzxKoNKDABIeBFSO6Ec15+7Xj4TH9SNtBiBCkHyFKuywpKsppiOlYOOSLJ5jsAAn3gGaY1zJU8/iVQhg0Ed+/9d08k//F3LmPsaz9IEIex9CiW8UQ8Ij80PCrIo0lLNfY/1o4XLUCiTR+sajkD3eHj4mBS7LqSJgdBVTrlFVOCQLAgBMHvB8z3VNP4oCGppzR+QJCEAFhAMqaEoZEFBTddVSxdUiFCgwpAAGDGC8hWfxqnMCR4PkPjkAYPSzlAGO9yOby7imP33nP/1R5xCjtYcIQGG0jyq57ZMz8P6YVNAQAZS9SB0Cho8JtIw2rCKFDFsBeJ2FjgkgFxD/LU0VHRILMapBDClbYxAwLa8i/YnNh2n27PNUV1/A10oE4G3HKIb08SzKKBMDOO7TDhiecXEEIXjfSZJ+5AFIwDytkg/G5QimSxMwduJBeBvvDy2pplkUmQJO+7iKoFGdDwCahc7S5z9FvU/JG3lAgxkTq3h/QazPKT7x/r9M/THCYP0P/5MWv/hrmj33Iic+EYU1ZJAAMICYCIDgVUAAyAQAkmmQ/szhq3oviqAP/QkT2pE5fXMuyOBRwW8OfBN9CaX5iz/F0pmCafLsK1WDzyPRja0rHFb0vqMqmZUUaSNEUMLAuOJpRlPK4u/epI/PPYsZAAK6ZGQNfliDmJFXMIBzTQ6Y2XmWpk4/0YJIFaQIwFBAhcSpKoxB4VkMcACxQQaehBj3sQKY0gDLPZ5DEcicZyAPArzPwG+gKvof+f3fom7womnu4s9p+sIbTYPPUnvrRZCACoY1E6ACFBCzAOv9riQFeGqCcRU2hAAgkT0ZH+AQMqEBcs76f1DlKImwoVxA8usKNOD3q52sDhA8lkG+jWHaAYvXCRVAAsCxT/bHSH4o5MDFV7LXoSSHFtMghRXtEEoYkBuaqu8pozEImJX1zr5OvR3d23wWKmAqJCwAX1MoRCGEx5kBYv0vAkaUF2rKBSQ+5vx5ASIEAL9fjA/LC0398IIWG9NSAiRQAKECcsEhSXhahALcawFWhiyRMW+WkM05BxjedcFEics8D1DMU54T3yGF6uJXf4tQAbzNewajSnzjAtfYfKw1gUJaCoCAsXXF/YpKZxGACoIAcgCJcFhTYXXpiryuRKOBQ4CzPp6fkud68ggbIKPyDqCJf0IA0ByZCWqaNdrhUWXc8LzXAUo+GrxJATigIIYNE85JboCHJBc89HN/jLaectbM+edhqMdhRRHEFDi8pN8UOJJdAD3+UNdSnoy4hwASYF8BwwuX0kfzX6ehQ5cEWIuh2PejslN1ppcTBhzJA3iemh+gdd2HCAzwEDGqJSeAAOaBT7Cdpvka8N4jACTXbJjklaJmHbVBFOf0gUSI4JpzjHPewbOAJrQIHUrmlkIAAoY0FeJ5CMj7gJo11qSIjaf92GcvIJbDPUltWis4lrhsd7Hh0TguL+rlHSWZiHnJs6G4RQEQAHBIIBcAnvhH/s4FAMHbljheK4PB27QBAln72p7nWfpBBs9ZCfTjnb7P83if5NmRAr2ZUlGB0yzkDwlMg6iAMCD7ExqtLZGh8wE2Nnpbu2lyW5KR/NnsQAGAZ75HCez8AryrH2qJEO8EQwBex/uYZwMGzqAZKKCI57K0GTyGQkwSfbEyYM55BjN4QPv9PI8KqAHIAUyBhEFTnmUKDJDM90p+KGDsxKN+HQA5EDFQKeIeIjpaCU5JDbNn9TKBJwfgfUB3FGtsemIAZ0MUIli5EQ4owcUQgyS7M9Cupli2ycnaTFt5KoQErxbzlhmAc42Ql7bImiPPE/P0AzD38Tj3rABmCwhgQxSb0Cqwt/MyCIhsf/xBKKCythvb4qwBTMIAO7xsdEKCCcAAiZdRwIR+AAM88icJBgGEggjIMfi2bEXWgOGcI0CJVS9oPO3luT6XxCYgkpmsXBnmUje/wyTSHwLoSz/eyS4SOYC/GdTleSe8zvYLLY9fpdapZ1oU3eurIELABDD9EfsoAaPggQCk35PEIAAlQAwEjOoeBLigMQEubgCfyVDfYvBMdyYAEL7v57nHOSCpF6gAWSFy7j5WEEaIAD4rQiWvsj9rAXLAmDzvmr+9pVXm9svIAaEIGQTELEDScxjEHzyWsWt5BUj2F2jLn0Q4LBAQE+BleCDY1+Agg3N7yYOmnWsD9DmEUMlBkNshhecpplghugymjffwvhwGOa8Q/9zn+eHibwoogWRH/AOUKtDTIG222BVmj5+pr7lOYtPUVRDAn7qQeYCFef0AmT8IKJIPxsAYgMHjLWLSJNCGWdb2uhUC+DIBnJsASmDWDFyXCcDjTqp+L+0AJwl22EcskiAEkAQxcgDATU5VSogk2N/mLgioiRA2Oan4UAGyr2mwVQF/u+P7rrcNFgLwCgPDy/QDHOcMkjjH87TRh+d8D4MU2ngH4CO7F+Ax7nGdweddJ97Fs6wDmAk6Sm6AB2htPSdAZoGqkmF943Fkf+5FHUDsmwDkzzZ3PdSg2MVTGnDfmK/7c3De8AAEP+6B4f28NFW46Hk8yhHjGa6d7Jy8TBD3eBfXPM893sfRv+F4dw7gvE+OSmEU0ED2xHgR781NkXFSNYFCobkpQkUQBNSZBnPpq/mcdUCAV6YNNWTPI328b3tblGSplgng3Crg2B+Y7nFtwGXPowaDNnATwjl9+R3aOM/hxe9ANqGgOV8W7954GATU1vRuSl2Bb8jjEAD4yqqUdFLAixAIAlz3QwT7+h1eLKPmx+uOfecBTz9I1eDKQBmsgZoggzcYzg3KXucaICbA4Hkn19zH8ruymcz+ezflYYUAoQABqAC5N/oEsA+Q5Q8BEQLjFC54m21wvainkpIpz4seg2cGYEokAVZRQxEGHiggOGcgZSI8aPpAGmC4xrjnZwFPzPMOv4c+9Oc5Z3veHV5Xye3NF4/Df0xBBePFVMfOUGPjiWYA5QPlAFeA/STIdjb7euz+1PWyugqbpmpqAMcWVyx6BLJY9AA+CFA7P2yzIgyuDJB2+tizWLk/7fQzYfa6jX60+15swIoAdoHZCq9rvHxX0OjnAH6fvyvwd0PeKRWIgHER4NzQDwE2NL2t3RB4trohJLa3BBgSygSw69s3DYwBmgDnAgxAGIM2YMc6/Z0Uy/3KpNHP/Xmf72NtEdAWOKwJYIAXRiL0bGBrK/G1goSiLpBBQKwFemKxq2VkW0yywQkR7PZ6j89gAe+dX86DlGLwAASMB4zRZs/5Pub7LpE5L4M3ATYT6j5dtuA0zhbgCvAc41oGAWyMQgLntHVOPQ1rRT5414KAqW0tVmQLn/6Ups/kHR6WtYCtKjQA7n1/2mLBo/ayhzxIZ2VA42WyPMY5bZ7fkTvPmgSb3+E1AOcQwb0glFWnCOhI4lYByc8LIeeA7Hk5V0URCggVvI+AzsndBAnTp5+mo1/8NR248CqA9rY1fRQEOASsAK4xewxjkBzxkgeM7O1JBs817fRhqoQIan3PEPSBSO6bSL/PBCN/Nj6sAAhw8rPkbXll+CzkH/Y+ApB8W6Amtx6nhc9+im0uE+Cd3vC2kl45FNgCm9qhUlM/AcgqyGHBucEAEMPzqCDv6t4MAtjTW/q3v0e9z30A8hxmRfg9/AbP9E6z90doEoZKnFIDYB37lj2WFaBluOZ+rIUKNAswE/T3A9j5JfszxXm5S8HDOYsgDG8zFdpYIJUJoCihQIKArtbvh7/8zwBlMPYe5xCEZwEDMdT8lLsQYLnTzzmDcyuGkOjpmMejcJHjelIueaEhpwAeJSD7MgktTYWoobutkEIRkFImgI1Ppj2DpwDiGqAQwU4QROSpsagLBLYTxQjyx+N5SwtZ8/c7/mztMPDsADjAeLrzOe2+5mgSeIY2iIGgeE73HIIoFBIhps1WnMB7Q8Sq4Oj2XyWATQ82PQEIOIPEIGUS9mWQUKMKVOKiILLXXZd35X0GxDIWIiDAswOAAIcBzO17SQKwDcAAx/uc8w4KHzzvLbjynkEG/yRUEWGikOjXBZBAQiyB5zjAzi8WVaB+DG8TDngfIqgGIcH7Ange4PZ4XpOTrACXa3R7mAHjTYBxbq9yNGjff59ZMSiAdwYxeu+ExgkBhAHyhyTu8ZVZS/mLRFlVgQcBhIBJoDBqKmc0mO6VSIOA8uZnedOTc+QPCYQF6oAA79mxgck5W97s9+VkSMZ+O3hIYOBcWwVu59oEcG0iOJY9jzk/RLsAf3zxx/wlisIgKkLds2o4p40dYkLhwCdvgoQIBY57CZjQSyHAW98AZhXIF59YyJ4wkQo4x8N4nxCACO/585cgziHFQA0EYJ4CAUtbBpxDhzaTAAhAI2vL2zMNNqHZ6eiXebpGBbyrbCYNJQB+6at/xBekJEZCpEn5XJAQBBD7GCQ49lEBHzuZABMTRBQhwGKIwVsBeWnqcGDRklViZbCCzEVPXvmVFeNnIS8T4lAiD2TzPUJgRqTwJ7mYiZRrMBMASfw+TqD9sGqbjz/9uf8dMQTw4RTfEWJBAPGPOesDmhBw9qfNCRJgNgYJYIMxEEjhPipxO30BD3mAcRugeAfm67KZDI5hGiMfZFOrkAQBXQ4ByEVxqI17BxQufGiJCiIUREBbiXJiR6pQ1Rs5oK0fwqYEZFIGaCSf/xgCGZmQUMMJVm4ZfGfLoBkIEs0hgiIYPNvjKIV2f0JvUCaR825BQNnTZQJotwUZmgUoyBoaH2Gz1/A+hhqmzmqa5JNZeT8qSBEA8N7ZF0HCQFcvfEvAI3WmtmYxocR3gmRIHUDmVXV4WnO22honyb5qF/CW+kJIJoAB3AmwDJ6/4UECQAHov/wAgjbOIZB7WB+gQJeNdvdjBujK4zE1U0CR9Aorq8H5h3zAn80iNJglmDUKAgoFaBCFtTfk2T1W/hgaY8sMEjoCizXVJwjZ5kczASjCIYACOAdY/kNGlrzB0Z5VlBX0PuuDV2hRCbJhQ46ao4oskh7mihICTAbATVCcS/4Ax8gF8bdBE1Bb4T80tBQWILwN4L2EmABfmwDAWw11vdMeZtAAMCG0kTNo49z1hIEabNl4R4DXcxz5hwwImL/4ut/GMb8XcvPsAGCfm6SJAjx5gJkgCODbX6xaIoBQgAS8D1BAExb+IDpCQQYBHRHFMRsJKEudUFj4nK85XwZA2jgyYIBx7dnESjBgAzJAt3Gfr9FRwZymSd93X3saNWT553XHXgKYAUiIQQB/FMECEN4sJM9n8BxD6mqHAM65bwLwOvJHBRDAf4sYEATwNYcJoM3eNgmALivAYHjWZnB+hmqUqvWAQoCEW+4LSDzu/QSuTUYY06eSXz8H4Hn+Joj1lMjwvL//Bygq4NqgTQqALX0IqBdqIBlaygwY8AwMUoh5jhhA+MKDb4AMjKOtDAorE8BsRI0yrwUXRRjvoRDDYsWoe5hJMAERCqogJ7Xkn77wOk2df1UiQLE9KbBTO+pUEID0Ocec9ExAhIYMAggBzrMa+IsuP0gyyp4FMFIn3svZH/CHv/wlBm6ANnv9fQQgf2aBGRU6GXT+PT8HUM8EAHdF2UUBEEMhJQKmVU1GCIQJMP8GY8B9kMgaYLp2G0RwDnATgQq45uhBAb7sdcyk0E4fSMCDXk8A0FYmokwA8rdN4eXwMFO4Smi9hza+UyL+Ac7qlC/KIiQgAOIUPtPnXmYFxCwg8A3lAQADjlAAaPylSOTkWqAUGjrHOKetVyTNIEA/NCVwDNaADYpBAjymRpHAwC1fQDp8MPpzP8fxW4Ugf69c+VYRAnpqRxEkxrnzLzVWCMmf0vERJbtPKCEIU7/4bxKVxwOOcQiw7O1lLM7xMARQFKESHfmsBos2EUb+wCCAPvGZDUohIxeeYoCz8vYsCxzkCEB5KQAJQJS4WEGA53AIcM0PKFaCAI/NXB2ndY3NKKnFPdmkFk20Qcb8J69jymQBRe3ALhRkzCgRBgEhZw12r9zd3pUB9NfMZAQhBSlYkAaAImYBijowb7KYAM6p6sLwrgzwHEliSDkIiOefBdh3TG1s7EIK4IMAXc8o48+oHMbY77SxoKJtwCBtqIFkiIU6dL8M9n3Gl2Z9EwH+zGay8HBeWEkZMpbfPbWjCpMQIYMKaIMMCJI5mwO8TACgZv/JSgSowsM4p22SMEExhTog0PbrBCDpkjffZz3ZZJiesemZGa0pZnaQoAABlnhWP4dRTLcF8MjkANZ1hIoAR6gIMLkC20tAqEj9bHy/CAEc32lX/zm8raPficVvFjbgjN+XOx5XW1/SHnRx3bcCMP9g/Y7p3rRA8+0hBLxVQyYV4xoQs8Wg7H2HgwdL3sBIkJ7K4roAyterWBDAMzLObbyDYiknxrcWJBSJdyCkDgkC/zbhSQkiAqCTKCIGvcfUNi2b2WP8zzFfnKMM+qCGWalhln+rfcfwzvMwvO9QYUk+o6mMAUJGmK5nPfAC3F4C5tR/1laQMUehpUJprwUJJOMLygGTp+XNbSlgk/WAVnkbTHl8Licp7/DNIMlEwHRdthm19a1MAOrRu7CprXu6vxv/gTp3VlWhbLZvTzRoBg4YgGSVcD6jezPnJH9NrRjFWQaXCeDj7QN4s3jeNivjOfpn09j1rn8y9Zk5r/4XnqX/BwUt2/n+Y6CCAAAAAElFTkSuQmCC");

        Call<Users> call = telemedServ.userReg(users);

        call.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {

                if (!response.isSuccessful()) {
                    return;
                }
            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {

            }
        });

    }
}
