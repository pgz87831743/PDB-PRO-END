package com.project.app.basic.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author admin
 * @since 2023
 */
@Getter
@Setter
@TableName("sys_user")
@Schema( description = "用户表")
@NoArgsConstructor
@AllArgsConstructor
public class SysUser implements UserDetails {

    @Schema(description="主键")
    private String id;

    @Schema(description="用户名")
    private String username;

    @Schema(description="密码")
    private String password;

    @Schema(description="昵称")
    private String nickname;

    @Schema(description="头像")
    private String avatar;

    @Schema(description="角色")
    private String role;

    @Schema(description="创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @Schema(description="创建人")
    @TableField(fill = FieldFill.INSERT)
    private String createBy;





    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = Stream.of(getRole()).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        List<SimpleGrantedAuthority> role = Stream.of(getRole()).map(s -> new SimpleGrantedAuthority("ROLE_" + s)).collect(Collectors.toList());
        authorities.addAll(role);
        return role;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }
}
